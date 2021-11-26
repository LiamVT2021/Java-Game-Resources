package heap;

import java.util.function.IntSupplier;

public class PrimMiddleQueue extends PrimQueue {

    private PrimHeap top;
    private PrimHeap bottom;
    private IntSupplier supply;

    public PrimMiddleQueue(int bottomSize, int queueSize, int topSize) {
        super(queueSize);
        lock();
        top = new PrimHeap(false, topSize).lock();
        bottom = new PrimHeap(true, topSize).lock();
    }

    @Override
    public boolean push(int i) {
        if (top.push(i))
            return true;
        if (bottom.push(top.swap(i)))
            return true;
        if (super.push(bottom.swap(i)))
            return true;
        return false;
    }

    @Override
    public int primPop() {
        if (!super.isEmpty())
            return super.primPop();
        push(supply.getAsInt());
        return super.primPop();
    }

    @Override
    public int primPeek() {
        if (!super.isEmpty())
            return super.primPeek();
        push(supply.getAsInt());
        return super.peek();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && supply == null;
    }

}
