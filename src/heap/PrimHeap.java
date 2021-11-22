package heap;

public abstract class PrimHeap extends HeapADT implements PrimPushPop {

    public PrimHeap(int cap, boolean max) {
        super(cap);
        this.max = max;
    }

    private boolean max;

    @Override
    public boolean push(int i) {
        if (isFull())
            return false;
        heapUp(size++, i);
        return true;
    }

    @Override
    public int primPop() {
        int ret = peek();
        heapDown(0, get(--size));
        return ret;
    }

    @Override
    public int primPeek() {
        return get(0);
    }

    @Override
    protected void heapUp(int index) {
        heapUp(index, get(index));
    }

    private void heapUp(int index, int value) {
        while (index > 0) {
            int up = up(index);
            int top = get(up);
            if (compare(value, top))
                index = helpHeap(index, up, top);
            else {
                set(index, value);
                break;
            }
        }
    }

    @Override
    protected void heapDown(int index) {
        heapDown(index, get(index));
    }

    private void heapDown(int index, int value) {
        int bottom = bottom();
        while (index <= bottom) {
            int l = left(index);
            int left = get(l);
            int r = l + 1;
            if (r >= size) {
                if (compare(left, value))
                    index = helpHeap(index, l, left);
                else
                    break;
            }
            int right = get(r);
            if (compare(left, value) && !compare(right, left))
                index = helpHeap(index, l, left);
            else if (compare(right, value))
                index = helpHeap(index, r, right);
            else
                break;
        }
        set(index, value);
    }

    private int helpHeap(int index, int next, int value) {
        set(index, value);
        return next;
    }

    private boolean compare(int a, int b) {
        return max ? a < b : a > b;
    }

    protected abstract int get(int index);

    protected abstract void set(int index, int value);

    public static class Int extends PrimHeap {

        public Int(int cap, int length, boolean max) {
            super(cap, max);
            arr = new int[length];
        }

        private int[] arr;

        @Override
        protected int get(int index) {
            return arr[index];
        }

        @Override
        protected void set(int index, int value) {
            arr[index] = value;
        }

    }

}
