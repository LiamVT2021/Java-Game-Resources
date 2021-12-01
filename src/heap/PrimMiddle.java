package heap;

import java.util.function.IntSupplier;

public class PrimMiddle extends PrimStack.Int {

    private PrimHeap top;
    private PrimHeap bottom;

    public PrimMiddle(int bottomSize, int queueSize, int topSize) {
        super(queueSize);
        lock();
        top = new PrimHeap.Int(false, topSize).lock();
        bottom = new PrimHeap.Int(true, bottomSize).lock();
    }

    private PrimMiddle(PrimStack.Int queue, PrimHeap top, PrimHeap bottom) {
        super(queue.array());
        this.top = top;
        this.bottom = bottom;
    }

    public boolean setHeapSize(int bottomSize, int topSize) {
        if (bottomSize < 0 || topSize < 0 || !hasRoom(bottom.size() - bottomSize + top.size() - topSize))
            return false;
        top.setRange(topSize);
        bottom.setRange(bottomSize);
        while (top.isOver())
            push(top.pop());
        while (bottom.isOver())
            push(top.bottom());
        while (top.hasRoom() && !bottom.isEmpty())
            top.push(bottom.primPop());
        while (top.hasRoom() && !super.isEmpty())
            top.push(super.pop());
        while (bottom.hasRoom() && !super.isEmpty())
            bottom.push(super.pop());
        top.trim();
        bottom.trim();
        return true;
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

    public Supply withSupply(IntSupplier supply) {
        return new Supply(this, supply);
    }

    public class Supply extends PrimMiddle implements Supplied.Prim {

        private Supply(PrimMiddle middleQueue, IntSupplier supply) {
            super(middleQueue, middleQueue.top, middleQueue.bottom);
            this.supply = supply;
        }

        private IntSupplier supply;

        public IntSupplier supply() {
            return supply;
        }

        @Override
        public Supply withSupply(IntSupplier supply) {
            this.supply = supply;
            return this;
        }

        @Override
        public boolean isFull() {
            return super.isFull();
        }

        @Override
        public int primPop() {
            prepare();
            return super.primPop();
        }

        @Override
        public int primPeek() {
            prepare();
            return super.primPeek();
        }

        public boolean prepare() {
            if (supply == null)
                return !super.isEmpty();
            while (super.isEmpty())
                push(supply.getAsInt());
            return true;
        }

        @Override
        public boolean isEmpty() {
            return super.isEmpty() && supply == null;
        }

    }

}
