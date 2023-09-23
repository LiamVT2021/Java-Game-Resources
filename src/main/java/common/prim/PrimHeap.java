package common.prim;

import java.util.function.BiPredicate;

import common.prim.array.*;
import common.pushPop.Heap;

public abstract class PrimHeap<N extends Number, A> extends Heap<N, Number, A> implements PrimPushPop<N> {

    private PrimHeap(PrimArray<N, A> array, BiPredicate<Number, Number> belongsAbove) {
        super(array, belongsAbove);
    }

    public static class ByteHeap extends PrimHeap<Byte, byte[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.byteValue() > b.byteValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.byteValue() < b.byteValue();

        public ByteHeap(int capacity, boolean max) {
            super(new ByteArray(capacity), max ? MAX : MIN);
        }

    }

    public static class ShortHeap extends PrimHeap<Short, short[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.shortValue() > b.shortValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.shortValue() < b.shortValue();

        public ShortHeap(int capacity, boolean max) {
            super(new ShortArray(capacity), max ? MAX : MIN);
        }

    }

    public static class IntHeap extends PrimHeap<Integer, int[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.intValue() > b.intValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.intValue() < b.intValue();

        public IntHeap(int capacity, boolean max) {
            super(new IntArray(capacity), max ? MAX : MIN);
        }

    }

    public static class LongHeap extends PrimHeap<Long, long[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.longValue() > b.longValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.longValue() < b.longValue();

        public LongHeap(int capacity, boolean max) {
            super(new LongArray(capacity), max ? MAX : MIN);
        }

    }

    public static class FloatHeap extends PrimHeap<Float, float[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.floatValue() > b.floatValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.floatValue() < b.floatValue();

        public FloatHeap(int capacity, boolean max) {
            super(new FloatArray(capacity), max ? MAX : MIN);
        }

    }

    public static class DoubleHeap extends PrimHeap<Double, double[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.doubleValue() > b.doubleValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.doubleValue() < b.doubleValue();

        public DoubleHeap(int capacity, boolean max) {
            super(new DoubleArray(capacity), max ? MAX : MIN);
        }

    }

}
