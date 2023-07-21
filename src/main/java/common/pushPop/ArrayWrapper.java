package common.pushPop;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.dataStruct.IterableExt;
import common.util.StringUtils;

/**
 * Wrapper interface around an array.
 * 
 * @param <G> the type returned by get methods
 * @param <S> the type consumed by set methods
 * @param <A> the type of the wrapped array
 * @version 7/12/23
 */
public interface ArrayWrapper<G extends S, S, A> extends IterableExt<G> {

    /**
     * @return the unwrapped array
     */
    A array();

    /**
     * @return the capacity of this array
     */
    int capacity();

    /**
     * @return the value stored at this index in the array
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    G get(int index);

    /**
     * @return A Stream of values retrieved from the corresponding indexes
     * @throws IndexOutOfBoundsException if any index is out of bounds
     */
    default Stream<G> get(int... indexes) {
        return get(IntStream.of(indexes));
    }

    /**
     * @return A Stream of values retrieved from the corresponding indexes
     * @throws IndexOutOfBoundsException if any index is out of bounds
     */
    default Stream<G> get(IntStream indexes) {
        return indexes.mapToObj(this::get);
    }

    /**
     * removes the value stored at this index if possible
     * 
     * @return the value stored at this index in the array
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    G remove(int index);

    /**
     * stores value at index in the array
     * 
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    void set(int index, S value);

    /**
     * performs a set and get at the same time
     * stores value at index in the array
     * 
     * @return the value that was at index in the array
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    default G swap(int index, S value) {
        G ret = get(index);
        set(index, value);
        return ret;
    }

    /**
     * @return value cast into G type
     */
    G cast(S value);

    @Override
    default Iterator<G> iterator() {
        return iterator(capacity());
    }

    /**
     * @param size how many elements will be returned by this iterator
     */
    default Iterator<G> iterator(int size) {
        return new Iterator<>() {
            private int i;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public G next() {
                return get(i++);
            }
        };
    }

    /**
     * @param prefix characters at the begining of merged string
     * @param delim  characters betewen elements the array
     * @param suffix characters at the end of merged string
     * @return a merged string of the elements of this array
     */
    default String toString(CharSequence prefix, CharSequence delim, CharSequence suffix) {
        return StringUtils.join(prefix, delim, suffix, stream().map(G::toString));
    }

    /**
     * @return String of elements of this array
     *         with format "[ a, b, c, d, e ]"
     */
    default String arrayString() {
        return toString("[ ", ", ", " ]");
    }

    public static abstract class ADT<G extends S, S, A> implements ArrayWrapper<G, S, A> {

        protected final A array;

        protected ADT(A array) {
            this.array = array;
        }

        @Override
        public A array() {
            return array;
        }

        /**
         * @return String representation of this array
         *         with format "className: [ a, b, c, d, e ]"
         */
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + ": " + arrayString();
        }

    }

}
