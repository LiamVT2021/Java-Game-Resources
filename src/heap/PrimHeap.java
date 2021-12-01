package heap;

public interface PrimHeap extends Expand, PushPop.Prim, HeapADT {

    @Override
    public default boolean isEmpty() {
        return Expand.super.isEmpty();
    }

    @Override
    public default boolean push(int i) {
        if (!expand())
            return false;
        heapUp(sizePP(), i, true);
        return true;
    }

    @Override
    public default int primPop() {
        int ret = peek();
        heapDown(0, get(mmSize()), true);
        return ret;
    }

    @Override
    public default int primPeek() {
        return get(0);
    }

    @Override
    public default boolean heapUp(int index) {
        return heapUp(index, get(index), false);
    }

    @Override
    public default boolean heapDown(int index) {
        return heapDown(index, get(index), false);
    }

    private boolean heapUp(int start, int value, boolean writeOnStart) {
        int i = start;
        while (i > 0) {
            int up = up(i);
            int top = get(up);
            if (compare(value, top))
                i = helpHeap(i, up, top);
            else
                break;
        }
        return write(start, value, writeOnStart, i);
    }

    private boolean heapDown(int start, int value, boolean writeOnStart) {
        int i = start;
        int bottom = bottom();
        while (i <= bottom) {
            int l = left(i);
            int left = get(l);
            int r = l + 1;
            if (r >= size()) {
                if (compare(left, value))
                    i = helpHeap(i, l, left);
                else
                    break;
            }
            int right = get(r);
            if (compare(left, value) && !compare(right, left))
                i = helpHeap(i, l, left);
            else if (compare(right, value))
                i = helpHeap(i, r, right);
            else
                break;
        }
        return write(start, value, writeOnStart, i);
    }

    private int helpHeap(int index, int next, int value) {
        set(index, value);
        return next;
    }

    private boolean write(int start, int value, boolean writeOnStart, int i) {
        if (i == start && !writeOnStart)
            return false;
        set(i, value);
        return true;
    }

    private boolean compare(int a, int b) {
        return max() ? a < b : a > b;
    }

    public default PrimHeap lock() {
        return (PrimHeap) Expand.super.lock();
    }

    public boolean max();

    public void set(int index, int value);

    public int get(int index);

    public class Int extends Expand.Int implements PrimHeap {
        public Int(boolean max, int length) {
            super(length);
            this.max = max;
        }

        public Int(boolean max, int... ints) {
            super(ints);
            this.max = max;
        }

        private boolean max;

        @Override
        public boolean max() {
            return max;
        }
    }

    public class Short extends Expand.Short implements PrimHeap {
        public Short(boolean max, int length) {
            super(length);
            this.max = max;
        }

        public Short(boolean max, short... ints) {
            super(ints);
            this.max = max;
        }

        private boolean max;

        @Override
        public boolean max() {
            return max;
        }
    }

    public class Byte extends Expand.Byte implements PrimHeap {
        public Byte(boolean max, int length) {
            super(length);
            this.max = max;
        }

        public Byte(boolean max, byte... ints) {
            super(ints);
            this.max = max;
        }

        private boolean max;

        @Override
        public boolean max() {
            return max;
        }
    }
}
