package heap;

import java.util.Comparator;

public abstract class Heap<E> extends HeapADT implements PushPop<E> {

    protected E[] arr;

    @Override
    public boolean push(E e) {
        if (isFull())
            return false;
        // arr[size] = e;
        // heapUp(size);
        // size++;
        heapUp(size++, e);
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        E ret = arr[0];
        arr[0] = arr[size--];
        heapDown(0);
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
        E peek = peek();
        if (compare(peek, e)) {
            arr[0] = e;
            heapDown(0);
            return peek;
        }
        return e;
    }

    @Override
    public void swapIndex(int a, int b) {
        E temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    @Override
    public boolean compareIndex(int a, int b) {
        E A = arr[a];
        if (A == null)
            return false;
        E B = arr[b];
        if (B == null)
            return true;
        return compare(A, B);
    }

    public abstract boolean compare(E A, E B);

    protected void heapUp(int i, E e) {
        int up = up(i);
        E top = arr[up];
        if (compare(e, top)) {
            arr[i] = top;
            heapUp(up, e);
        } else
            arr[i] = e;
    }

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
