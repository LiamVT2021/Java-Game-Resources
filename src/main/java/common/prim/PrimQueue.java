package common.prim;

import common.prim.array.*;
import common.pushPop.ArrayWrapper;
import common.pushPop.Queue;

public abstract class PrimQueue<N extends Number, A> extends Queue<N, Number, A> {

    public PrimQueue(ArrayWrapper<N, Number, A> array) {
        super(array);
    }

    public static class Byte extends PrimQueue<java.lang.Byte, byte[]> {

        public Byte(int capacity) {
            super(new ByteArray(capacity));
        }

    }

    public static class Short extends PrimQueue<java.lang.Short, short[]> {

        public Short(int capacity) {
            super(new ShortArray(capacity));
        }

    }

    public static class Int extends PrimQueue<Integer, int[]> {

        public Int(int capacity) {
            super(new IntArray(capacity));
        }

    }

    public static class Long extends PrimQueue<java.lang.Long, long[]> {

        public Long(int capacity) {
            super(new LongArray(capacity));
        }

    }

    public static class Float extends PrimQueue<java.lang.Float, float[]> {

        public Float(int capacity) {
            super(new FloatArray(capacity));
        }

    }

    public static class Double extends PrimQueue<java.lang.Double, double[]> {

        public Double(int capacity) {
            super(new DoubleArray(capacity));
        }

    }

}
