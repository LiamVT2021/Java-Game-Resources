package heap;

import util.Sized;

public interface HeapADT extends Sized {

    default int up(int i) {
        return (i - 1) / 2;
    }

    default int left(int i) {
        return i * 2 + 1;
    }

    default boolean isLeft(int i) {
        return i > 0 && i % 2 == 1;
    }

    default boolean isRight(int i) {
        return i > 0 && i % 2 == 0;
    }

    default int bottom() {
        return up(size() - 1);
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

    public boolean heapUp(int i);

    public boolean heapDown(int i);

    public default void heapify() {
        for (int i = bottom(); i >= 0; i--)
            heapDown(i);
    }

    public default void update(int index) {
        heapUp(index);
        heapDown(index);
    }

}
