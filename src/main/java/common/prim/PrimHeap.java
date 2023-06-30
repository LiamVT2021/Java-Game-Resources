package common.prim;

import java.util.function.BiPredicate;

import common.prim.array.*;
import common.pushPop.Heap;

public abstract class PrimHeap<N extends Number, A> extends Heap<N, Number, A> {

    private PrimHeap(PrimArray<N, A> array, BiPredicate<Number, Number> belongsAbove) {
        super(array, belongsAbove);
    }

    public static class Byte extends PrimHeap<java.lang.Byte, byte[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.byteValue() > b.byteValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.byteValue() < b.byteValue();

        public Byte(int capacity, boolean max) {
            super(new ByteArray(capacity), max ? MAX : MIN);
        }

    }

    public static class Short extends PrimHeap<java.lang.Short, short[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.shortValue() > b.shortValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.shortValue() < b.shortValue();

        public Short(int capacity, boolean max) {
            super(new ShortArray(capacity), max ? MAX : MIN);
        }

    }

    public static class Int extends PrimHeap<Integer, int[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.intValue() > b.intValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.intValue() < b.intValue();

        public Int(int capacity, boolean max) {
            super(new IntArray(capacity), max ? MAX : MIN);
        }

    }

    public static class Long extends PrimHeap<java.lang.Long, long[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.longValue() > b.longValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.longValue() < b.longValue();

        public Long(int capacity, boolean max) {
            super(new LongArray(capacity), max ? MAX : MIN);
        }

    }

    public static class Float extends PrimHeap<java.lang.Float, float[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.floatValue() > b.floatValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.floatValue() < b.floatValue();

        public Float(int capacity, boolean max) {
            super(new FloatArray(capacity), max ? MAX : MIN);
        }

    }

    public static class Double extends PrimHeap<java.lang.Double, double[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.doubleValue() > b.doubleValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.doubleValue() < b.doubleValue();

        public Double(int capacity, boolean max) {
            super(new DoubleArray(capacity), max ? MAX : MIN);
        }

    }

}
