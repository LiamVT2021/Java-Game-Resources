package common.prim;

import java.util.function.BiPredicate;

import common.prim.array.*;
import common.pushPop.Heap;

public abstract class PrimHeap<N extends Number, A> extends Heap<N, Number, A> {

    public PrimHeap(PrimArray<N, A> array, BiPredicate<Number, Number> belongsAbove) {
        super(array, belongsAbove);
    }

    public static abstract class Int extends PrimHeap<Integer, int[]> {

        private static final BiPredicate<Number, Number> MAX = (a, b) -> a.intValue() > b.intValue();
        private static final BiPredicate<Number, Number> MIN = (a, b) -> a.intValue() < b.intValue();

        public Int(int capacity, boolean max) {
            super(new IntArray(capacity), max ? MAX : MIN);
        }

    }

}
