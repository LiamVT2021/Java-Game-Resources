package common.prim.array;

import java.util.function.Consumer;

/**
 * A wrapper around an array of byte values.
 * 
 * @version 6/29/23
 */
public class ByteArray extends PrimArray.Int<Byte, byte[]> {

    public ByteArray(int size) {
        this(new byte[size]);
    }

    public ByteArray(byte[] array) {
        super(array);
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public Byte get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, Number value) {
        array[index] = value.byteValue();
    }

    @Override
    public Byte cast(Number value) {
        return value.byteValue();
    }

    @Override
    public void forEach(Consumer<? super Byte> consumer) {
        for (byte v : array)
            consumer.accept(v);
    }

}
