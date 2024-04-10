package common.prim.array;

import java.util.stream.IntStream;

/**
 * A wrapper around an array of int values.
 * 
 * @version 4/10/24
 */
public class IntArray extends PrimArray.Int<Integer, int[]> {

    public IntArray(int size) {
        this(new int[size]);
    }

    public IntArray(int[] array) {
        super(array);
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public Integer get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, Number value) {
        array[index] = value.intValue();
    }

    @Override
    public Integer cast(Number value) {
        return value.intValue();
    }

    /**
     * @return a IntStream of the values in the array
     */
    public IntStream intStream() {
        return IntStream.of(array);
    }

    @Override
    public Integer sum(Number a, Number b) {
        return a.intValue() + b.intValue();
    }

    @Override
    public Integer product(Number a, Number b) {
        return a.intValue() * b.intValue();
    }

}
