package common.prim.array;

/**
 * A wrapper around an array of byte values.
 * 
 * @version 4/10/24
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
    public Byte sum(Number a, Number b) {
        return (byte) (a.byteValue() + b.byteValue());
    }

    @Override
    public Byte product(Number a, Number b) {
        return (byte) (a.byteValue() * b.byteValue());
    }

}
