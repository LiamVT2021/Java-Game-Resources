package common.prim;

import common.prim.array.*;
import common.pushPop.ArrayWrapper;
import common.pushPop.Stack;

public abstract class PrimStack<N extends Number, A> extends Stack<N, Number, A> implements PrimPushPop<N> {

    private PrimStack(ArrayWrapper<N, Number, A> array) {
        super(array);
    }

    public static class ByteStack extends PrimStack<Byte, byte[]> {

        public ByteStack(int capacity) {
            super(new ByteArray(capacity));
        }

    }

    public static class ShortStack extends PrimStack<Short, short[]> {

        public ShortStack(int capacity) {
            super(new ShortArray(capacity));
        }

    }

    public static class IntStack extends PrimStack<Integer, int[]> {

        public IntStack(int capacity) {
            super(new IntArray(capacity));
        }

    }

    public static class LongStack extends PrimStack<Long, long[]> {

        public LongStack(int capacity) {
            super(new LongArray(capacity));
        }

    }

    public static class FloatStack extends PrimStack<Float, float[]> {

        public FloatStack(int capacity) {
            super(new FloatArray(capacity));
        }

    }

    public static class DoubleStack extends PrimStack<Double, double[]> {

        public DoubleStack(int capacity) {
            super(new DoubleArray(capacity));
        }

    }

}
