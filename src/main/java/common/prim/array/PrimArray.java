package common.prim.array;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Wrapper interface around an array of primitive numbers.
 * 
 * @param <N> the type of numbers returned by get methods.
 * @param <A> the type of the wrapped array.
 * @version 4/10/24
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

    /**
     * @return the sum of a and b
     */
    N sum(Number a, Number b);

    /**
     * adds modifier to the value at index
     * 
     * @return the sum
     */
    default N add(int index, Number modifier) {
        return modify(index, n -> sum(n, modifier));
    }

    /**
     * adds modifier to all values in array
     */
    default void addAll(Number modifier) {
        moidfyAll(n -> sum(n, modifier));
    }

    /**
     * adds modifier to value for all provided indexes
     */
    default void addAll(Number modifier, int... indexes) {
        moidfyAll(n -> sum(n, modifier), indexes);
    }

    /**
     * adds modifier to value for all provided indexes
     */
    default void addAll(Number modifier, IntStream indexes) {
        moidfyAll(n -> sum(n, modifier), indexes);
    }

    /**
     * @return the product of a and b
     */
    N product(Number a, Number b);

    /**
     * multiplys the value at index by modifier
     *
     * @return the product
     */
    default N multiply(int index, Number modifier) {
        return modify(index, n -> product(n, modifier));
    }

    /**
     * multiplys by modifier for all values in array
     */
    default void multiplyAll(Number modifier) {
        moidfyAll(n -> product(n, modifier));
    }

    /**
     * multiplys the value by modifier for all provided indexes
     */
    default void multiplyAll(Number modifier, int... indexes) {
        moidfyAll(n -> product(n, modifier), indexes);
    }

    /**
     * multiplys the value by modifier for all provided indexes
     */
    default void multiplyAll(Number modifier, IntStream indexes) {
        moidfyAll(n -> product(n, modifier), indexes);
    }

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
