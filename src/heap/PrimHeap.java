package heap;

public class PrimHeap extends Expand.Int implements PushPop.Prim, HeapADT {

    public PrimHeap(boolean max, int length) {
        super(length);
        this.max = max;
    }

    public PrimHeap(boolean max, int... ints) {
        super(ints);
        this.max = max;
    }

    private boolean max;

    @Override
    public boolean push(int i) {
        if (!expand())
            return false;
        heapUp(size++, i, true);
        return true;
    }

    @Override
    public int primPop() {
        int ret = peek();
        heapDown(0, get(--size), true);
        return ret;
    }

    @Override
    public int primPeek() {
        return get(0);
    }

    @Override
    public boolean heapUp(int index) {
        return heapUp(index, get(index), false);
    }

    @Override
    public boolean heapDown(int index) {
        return heapDown(index, get(index), false);
    }

    private boolean heapUp(int start, int value, boolean writeOnStart) {
        int i = start;
        while (i > 0) {
            int up = up(i);
            int top = arr[up];
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
            int left = arr[l];
            int r = l + 1;
            if (r >= size) {
                if (compare(left, value))
                    i = helpHeap(i, l, left);
                else
                    break;
            }
            int right = arr[r];
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
        arr[i] = value;
        return true;
    }

    private boolean compare(int a, int b) {
        return max ? a < b : a > b;
    }

    private int get(int index) {
        return arr[index];
    }

    private void set(int index, int value) {
        arr[index] = value;
    }

    public PrimHeap lock() {
        return (PrimHeap) super.lock();
    }
}
