package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class IntArray extends PrimArray.ADT<Integer, int[]> {

    public IntArray(int size) {
        super(new int[size]);
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
    public void set(int index, Integer value) {
        array[index] = value;
    }

    @Override
    public void forEach(Consumer<Integer> consumer) {
        for (int v : array)
            consumer.accept(v);
    }

    public IntStream intStream() {
        return IntStream.of(array);
    }

}
