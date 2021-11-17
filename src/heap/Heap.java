package heap;

import java.util.Comparator;

public abstract class Heap<E> extends HeapADT implements PushPop<E> {

    protected E[] arr;

    @Override
    public boolean push(E e) {
        if (isFull())
            return false;
        heapUp(size++, e);
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        E ret = arr[0];
        E bot = arr[--size];
        arr[size] = null;
        heapDown(0, bot);
        return ret;
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return arr[0];
    }

    @Override
    public E swap(E e) {
        if (isEmpty())
            return e;
        E peek = arr[0];
        if (compare(peek, e)) {
            heapDown(0, e);
            return peek;
        }
        return e;
    }

    @Override
    protected void heapUp(int i) {
        heapUp(i, arr[i]);
    }

    private void heapUp(int i, E e) {
        while (i > 0) {
            int up = up(i);
            E top = arr[up];
            if (compare(e, top))
                i = helpHeap(i, up, top);
            else {
                arr[i] = e;
                break;
            }
        }
    }

    @Override
    protected void heapDown(int i) {
        heapDown(i, arr[i]);
    }

    private void heapDown(int i, E e) {
        int bottom = bottom();
        while(i < bottom) {
            int l = left(i);
            E left = arr[l];
            int r = l + 1;
            if (r >= size) {
                if (compare(left, e))
                    i = helpHeap(i, l, left);
                else
                    break;
            }
            E right = arr[r];
            if (compare(left, e) && !compare(right, left))
                i = helpHeap(i, l, left);
            else if (compare(right, e))
                i = helpHeap(i, r, right);
            else
                break;
        }
        arr[i] = e;
    }

    private int helpHeap(int i, int next, E e) {
        arr[i] = e;
        return next;
    }

    public abstract boolean compare(E A, E B);

    public static class Min<E extends Comparable<E>> extends Heap<E> {

        @Override
        public boolean compare(E A, E B) {
            return A.compareTo(B) < 0;
        }

    }

    public static class Max<E extends Comparable<E>> extends Heap<E> {

        @Override
        public boolean compare(E A, E B) {
            return A.compareTo(B) > 0;
        }

    }

    public static class Comp<E> extends Heap<E> {

        private Comparator<E> comp;

        @Override
        public boolean compare(E A, E B) {
            return comp.compare(A, B) < 0;
        }

    }

}
