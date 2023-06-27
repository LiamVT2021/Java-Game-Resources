package old.heap;

import java.util.function.IntSupplier;

public class PrimMiddle<P extends PrimArray<ArrType>, ArrType> implements PushPop.Prim {

    private PrimHeap<P, ArrType> top;
    private PushPop.Prim middle;
    private PrimHeap<P, ArrType> bottom;

    public PrimMiddle(PrimHeap<P, ArrType> bottom, Prim middle, PrimHeap<P, ArrType> top) {
        this.top = top;
        this.middle = middle;
        this.bottom = bottom;
    }

    // public boolean setHeapSize(int bottomSize, int topSize) {
    // if (bottomSize < 0 || topSize < 0 || !hasRoom(bottom.size() - bottomSize +
    // top.size() - topSize))
    // return false;
    // top.setRange(topSize);
    // bottom.setRange(bottomSize);
    // while (top.isOver())
    // push(top.pop());
    // while (bottom.isOver())
    // push(top.bottom());
    // while (top.hasRoom() && !bottom.isEmpty())
    // top.push(bottom.primPop());
    // while (top.hasRoom() && !super.isEmpty())
    // top.push(super.pop());
    // while (bottom.hasRoom() && !super.isEmpty())
    // bottom.push(super.pop());
    // top.trim();
    // bottom.trim();
    // return true;
    // }

    @Override
    public boolean push(int i) {
        if (top.push(i))
            return true;
        if (bottom.push(top.swap(i)))
            return true;
        if (middle.push(bottom.swap(i)))
            return true;
        return false;
    }

    @Override
    public int primPop() {
        return middle.primPop();
    }

    @Override
    public int primPeek() {
        return middle.primPeek();
    }

    @Override
    public boolean isEmpty() {
        return middle.isEmpty();
    }

    @Override
    public int size() {
        return top.size + bottom.size + middle.size();
    }

    @Override
    public int capacity() {
        return top.capacity() + bottom.capacity() + middle.capacity();
    }

    public Supply<P, ArrType> withSupply(IntSupplier supply) {
        return new Supply<>(this, supply);
    }

    public static class Supply<P extends PrimArray<ArrType>, ArrType> extends PrimMiddle<P, ArrType>
            implements Supplied.Prim {

        private Supply(PrimMiddle<P, ArrType> middleQueue, IntSupplier supply) {
            super(middleQueue.bottom, middleQueue.middle, middleQueue.top);
            this.supply = supply;
        }

        private IntSupplier supply;

        public IntSupplier supply() {
            return supply;
        }

        @Override
        public Supply<P, ArrType> withSupply(IntSupplier supply) {
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
