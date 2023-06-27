package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wrapper interface around an array of primitive numbers.
 * 
 * @version 6/27/23
 */
public interface PrimArray<N extends Number> {

    /**
     * @return the capacity of this array
     */
    int capacity();

    /**
     * @return the value stored at this index in the array
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    N get(int index);

    /**
     * stores value at index in the array
     * 
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    void set(int index, Number value);

    /**
     * performs a set and get at the same time
     * stores value at index in the array
     * 
     * @return the value that was at index in the array
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    default N swap(int index, Number value) {
        N ret = get(index);
        set(index, value);
        return ret;
    }

    /**
     * Iterates over each value in the array
     * 
     * @param consumer method inside for loop
     */
    void forEach(Consumer<N> consumer);

    /**
     * @return a Stream of the values in the array
     */
    default Stream<N> stream() {
        Stream.Builder<N> builder = Stream.builder();
        forEach(builder);
        return builder.build();
    }

    /**
     * @param prefix characters at the begining of merged string
     * @param delim  characters betewen elements the array
     * @param suffix characters at the end of merged string
     * @return a merged string of the elements of this array
     */
    default String toString(CharSequence prefix, CharSequence delim, CharSequence suffix) {
        return stream().map(N::toString).collect(Collectors.joining(delim, prefix, suffix));
    }

    /**
     * Abstract class implementation of PrimArray, with array field.
     */
    static abstract class ADT<N extends Number, A> implements PrimArray<N> {

        public final A array;

        public ADT(A array) {
            this.array = array;
        }

        @Override
        /**
         * @return string of elements of this array
         *         with format "[ a, b, c, d, e ]"
         */
        public String toString() {
            return toString("[ ", ", ", " ]");
        }

    }

}
