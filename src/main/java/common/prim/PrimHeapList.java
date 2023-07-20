package common.prim;

import common.prim.PrimHeap.ByteHeap;
import common.prim.PrimHeap.DoubleHeap;
import common.prim.PrimHeap.FloatHeap;
import common.prim.PrimHeap.IntHeap;
import common.prim.PrimHeap.LongHeap;
import common.prim.PrimHeap.ShortHeap;
import common.pushPop.HeapList;

public class PrimHeapList<N extends Number> extends HeapList<N, Number> {

    @SafeVarargs
    protected PrimHeapList(PrimHeap<N, ?>... heaps) {
        super(heaps);
    }

    public static PrimHeapList<Byte> middleByte(int dis, int adv) {
        return new PrimHeapList<Byte>(new ByteHeap(dis, true), new ByteHeap(adv, false));
    }

    public static PrimHeapList<Short> middleShort(int dis, int adv) {
        return new PrimHeapList<Short>(new ShortHeap(dis, true), new ShortHeap(adv, false));
    }

    public static PrimHeapList<Integer> middleInt(int dis, int adv) {
        return new PrimHeapList<Integer>(new IntHeap(dis, true), new IntHeap(adv, false));
    }

    public static PrimHeapList<Long> middleLong(int dis, int adv) {
        return new PrimHeapList<Long>(new LongHeap(dis, true), new LongHeap(adv, false));
    }

    public static PrimHeapList<Float> middleFloat(int dis, int adv) {
        return new PrimHeapList<Float>(new FloatHeap(dis, true), new FloatHeap(adv, false));
    }

    public static PrimHeapList<Double> middleDouble(int dis, int adv) {
        return new PrimHeapList<Double>(new DoubleHeap(dis, true), new DoubleHeap(adv, false));
    }
}
