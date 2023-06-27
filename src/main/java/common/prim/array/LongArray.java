package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.LongStream;

/**
 * A wrapper around an array of long values.
 * 
 * @version 6/27/23
 */
public class LongArray extends PrimArray.ADT<Long, long[]> {

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
    public void forEach(Consumer<? super Long> consumer) {
        for (long v : array)
            consumer.accept(v);
    }

    /**
     * @return a LongStream of the values in the array
     */
    public LongStream longStream() {
        return LongStream.of(array);
    }

}
