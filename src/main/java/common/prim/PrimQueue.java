package common.prim;

import common.prim.array.*;
import common.pushPop.ArrayWrapper;
import common.pushPop.Queue;

public abstract class PrimQueue<N extends Number, A> extends Queue<N, Number, A> {

    private PrimQueue(ArrayWrapper<N, Number, A> array) {
        super(array);
    }

    public static class ByteQueue extends PrimQueue<Byte, byte[]> {

        public ByteQueue(int capacity) {
            super(new ByteArray(capacity));
        }

    }

    public static class ShortQueue extends PrimQueue<Short, short[]> {

        public ShortQueue(int capacity) {
            super(new ShortArray(capacity));
        }

    }

    public static class IntQueue extends PrimQueue<Integer, int[]> {

        public IntQueue(int capacity) {
            super(new IntArray(capacity));
        }

    }

    public static class LongQueue extends PrimQueue<Long, long[]> {

        public LongQueue(int capacity) {
            super(new LongArray(capacity));
        }

    }

    public static class FloatQueue extends PrimQueue<Float, float[]> {

        public FloatQueue(int capacity) {
            super(new FloatArray(capacity));
        }

    }

    public static class DoubleQueue extends PrimQueue<Double, double[]> {

        public DoubleQueue(int capacity) {
            super(new DoubleArray(capacity));
        }

    }

}
