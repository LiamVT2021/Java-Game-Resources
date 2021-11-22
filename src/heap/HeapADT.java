package heap;

public abstract class HeapADT {

    public HeapADT(int cap) {
        this.cap = cap;
        this.size = 0;
    }

    protected int cap;
    protected int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == cap;
    }

    protected int up(int i) {
        return (i - 1) / 2;
    }

    protected int left(int i) {
        return i * 2 + 1;
    }

    protected boolean isLeft(int i) {
        return i > 0 && i % 2 == 1;
    }

    protected boolean isRight(int i) {
        return i > 0 && i % 2 == 0;
    }

    protected int bottom() {
        return up(size - 1);
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

    protected abstract boolean heapUp(int i);

    protected abstract boolean heapDown(int i);

    public void heapify() {
        for (int i = bottom(); i >= 0; i--)
            heapDown(i);
    }

    public void update(int index) {
        heapUp(index);
        heapDown(index);
    }

}
