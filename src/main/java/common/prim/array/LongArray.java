package common.prim.array;

import java.util.function.Consumer;
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

    public LongArray(long... array) {
        super(array);
    }

    @Override
    public long size() {
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

    @Override
    public void forEach(Consumer<? super Long> func) {
        for (long l : array)
            func.accept(l);
    }

    @Override
    public LongStream longStream() {
        return LongStream.of(array);
    }

}
