package common.prim.array;

import java.util.function.Consumer;

/**
 * A wrapper around an array of byte values.
 * 
 * @version 6/27/23
 */
public class ByteArray extends PrimArray.ADT<Byte, byte[]> {

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
    public void forEach(Consumer<Byte> consumer) {
        for (byte v : array)
            consumer.accept(v);
    }

}
