package common.prim.array;

/**
 * A wrapper around an array of short values.
 * 
 * @version 6/30/23
 */
public class ShortArray extends PrimArray.Int<Short, short[]> {

    public ShortArray(int size) {
        this(new short[size]);
    }

    public ShortArray(short[] array) {
        super(array);
    }

    @Override
    public long size() {
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

}
