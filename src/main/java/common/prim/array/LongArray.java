package common.prim.array;

import java.util.stream.LongStream;

/**
 * A wrapper around an array of long values.
 * 
 * @version 6/30/23
 */
public class LongArray extends PrimArray.Int<Long, long[]> {

    public LongArray(int size) {
        this(new long[size]);
    }

    public LongArray(long[] array) {
        super(array);
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public Long get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, Number value) {
        array[index] = value.longValue();
    }

    @Override
    public Long cast(Number value) {
        return value.longValue();
    }

    /**
     * @return a LongStream of the values in the array
     */
    public LongStream longStream() {
        return LongStream.of(array);
    }

}
