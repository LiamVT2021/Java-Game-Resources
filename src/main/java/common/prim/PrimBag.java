package common.prim;

import common.prim.array.*;
import common.pushPop.ArrayWrapper;
import common.pushPop.Bag;

public abstract class PrimBag<N extends Number, A> extends Bag<N, Number, A> implements PrimPushPop<N> {

    private PrimBag(ArrayWrapper<N, Number, A> array) {
        super(array);
    }

    public static class ByteBag extends PrimBag<Byte, byte[]> {

        public ByteBag(int capacity) {
            super(new ByteArray(capacity));
        }

    }

    public static class ShortBag extends PrimBag<Short, short[]> {

        public ShortBag(int capacity) {
            super(new ShortArray(capacity));
        }

    }

    public static class IntBag extends PrimBag<Integer, int[]> {

        public IntBag(int capacity) {
            super(new IntArray(capacity));
        }

    }

    public static class LongBag extends PrimBag<Long, long[]> {

        public LongBag(int capacity) {
            super(new LongArray(capacity));
        }

    }

    public static class FloatBag extends PrimBag<Float, float[]> {

        public FloatBag(int capacity) {
            super(new FloatArray(capacity));
        }

    }

    public static class DoubleBag extends PrimBag<Double, double[]> {

        public DoubleBag(int capacity) {
            super(new DoubleArray(capacity));
        }

    }

}
