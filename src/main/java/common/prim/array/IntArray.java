package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * A wrapper around an array of int values.
 * 
 * @version 6/29/23
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

    @Override
    public void forEach(Consumer<? super Integer> consumer) {
        for (int v : array)
            consumer.accept(v);
    }

    /**
     * @return a IntStream of the values in the array
     */
    public IntStream intStream() {
        return IntStream.of(array);
    }

}
