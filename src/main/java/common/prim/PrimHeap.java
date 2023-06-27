package common.prim;

import common.prim.array.*;
import common.pushPop.HeapADT;

public abstract class PrimHeap<N extends Number> extends HeapADT<N> {

    private final PrimArray<N> primArr;

    public PrimHeap(PrimArray<N> primArray) {
        primArr = primArray;
    }

    @Override
    public N get(int index) {
        return primArr.get(index);
    }

    @Override
    protected void set(int index, N value) {
        primArr.set(index, value);
    }

    @Override
    public int capacity() {
        return primArr.capacity();
    }

    public static PrimHeap<Integer> IntMin(int capacity) {
        return new PrimHeap<Integer>(new IntArray(capacity)) {
            @Override
            protected boolean belongsAbove(Integer a, Integer b) {
                return a < b;
            }
        };
    }
}
