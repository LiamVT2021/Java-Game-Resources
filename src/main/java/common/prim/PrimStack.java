package common.prim;

import common.prim.array.*;
import common.pushPop.ArrayWrapper;
import common.pushPop.Stack;

public abstract class PrimStack<N extends Number, A> extends Stack<N, Number, A> {

    private PrimStack(ArrayWrapper<N, Number, A> array) {
        super(array);
    }

    public static class Byte extends PrimStack<java.lang.Byte, byte[]> {

        public Byte(int capacity) {
            super(new ByteArray(capacity));
        }

    }

    public static class Short extends PrimStack<java.lang.Short, short[]> {

        public Short(int capacity) {
            super(new ShortArray(capacity));
        }

    }

    public static class Int extends PrimStack<Integer, int[]> {

        public Int(int capacity) {
            super(new IntArray(capacity));
        }

    }

    public static class Long extends PrimStack<java.lang.Long, long[]> {

        public Long(int capacity) {
            super(new LongArray(capacity));
        }

    }

    public static class Float extends PrimStack<java.lang.Float, float[]> {

        public Float(int capacity) {
            super(new FloatArray(capacity));
        }

    }

    public static class Double extends PrimStack<java.lang.Double, double[]> {

        public Double(int capacity) {
            super(new DoubleArray(capacity));
        }

    }

}
