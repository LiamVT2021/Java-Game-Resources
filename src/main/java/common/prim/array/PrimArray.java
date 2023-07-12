package common.prim.array;

import java.util.stream.Stream;

/**
 * Wrapper interface around an array of primitive numbers.
 * 
 * @param <N> the type of numbers returned by get methods.
 * @param <A> the type of the wrapped array.
 * @version 6/29/23
 */
public interface PrimArray<N extends Number, A> extends ArrayWrapper<N, Number, A> {

    @Override
    default Stream<N> stream() {
        Stream.Builder<N> builder = Stream.builder();
        forEach(builder);
        return builder.build();
    }

    @Override
    default N remove(int index) {
        return get(index);
    }

    /**
     * @return True if the array stores floating point numbers
     *         False if the array stores whole numbers
     */
    boolean storesFloat();

    static abstract class Int<N extends Number, A> extends ArrayWrapper.ADT<N, Number, A> implements PrimArray<N, A> {

        public Int(A array) {
            super(array);
        }

        @Override
        public boolean storesFloat() {
            return false;
        }

    }

    static abstract class Flt<N extends Number, A> extends ArrayWrapper.ADT<N, Number, A> implements PrimArray<N, A> {

        public Flt(A array) {
            super(array);
        }

        @Override
        public boolean storesFloat() {
            return true;
        }

    }

}
