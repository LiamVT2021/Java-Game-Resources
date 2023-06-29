package common.prim.array;

import java.util.function.Consumer;

/**
 * A wrapper around an array of short values.
 * 
 * @version 6/29/23
 */
public class ShortArray extends PrimArray.Int<Short, short[]> {

    public ShortArray(int size) {
        this(new short[size]);
    }

    public ShortArray(short[] array) {
        super(array);
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public Short get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, Number value) {
        array[index] = value.shortValue();
    }

    @Override
    public Short cast(Number value) {
        return value.shortValue();
    }

    @Override
    public void forEach(Consumer<? super Short> consumer) {
        for (short v : array)
            consumer.accept(v);
    }

}
