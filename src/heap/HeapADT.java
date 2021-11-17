package heap;

public abstract class HeapADT {

    protected int cap;
    protected int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == cap;
    }

    /**
     * @param a
     * @param b
     * @return true if a MUST be above b
     */
    // protected abstract boolean compareIndex(int a, int b);

    // protected abstract void swapIndex(int a, int b);

    protected int up(int i) {
        return (i - 1) / 2;
    }

    protected int left(int i) {
        return i * 2 + 1;
    }

    protected int bottom(){

    }

    // public default int right(int i){
    // return i*2+2;
    // }

    // public int row(int i) {
    // int r = 0;
    // for (i = (i + 1) / 2; i > 0; i /= 2)
    // r++;
    // return r;
    // }

    protected abstract void heapUp(int i);
    // int up = up(i);
    // if (compareIndex(i, up)) {
    // swapIndex(i, up);
    // heapUp(up);
    // }

    protected abstract void heapDown(int i);
    // int l = left(i);
    // int r = l + 1;
    // if (compareIndex(l, i) && !compareIndex(r, l)) {
    // swapIndex(l, i);
    // heapDown(l);
    // } else if (compareIndex(r, i)) {
    // swapIndex(r, i);
    // heapDown(r);
    // }

    public void heapify() {
        for (int i = bottom(); i >= 0; i--)
            heapDown(i);
    }

    public void update(int index) {
        heapUp(index);
        heapDown(index);
    }

}
