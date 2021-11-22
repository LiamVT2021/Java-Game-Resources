package heap;

import java.lang.reflect.Array;
import java.util.Comparator;

public abstract class Heap<E> extends HeapADT implements PushPop<E>, Cloneable {

    @SuppressWarnings("unchecked")
    public Heap(Class<E> Class, int cap, int length) {
        super(cap);
        arr = (E[]) Array.newInstance(Class, length);
    }

    public Heap(int cap, E[] arr) {
        this(cap, arr, true);
    }

    private Heap(int cap, E[] arr, boolean heapify) {
        super(cap);
        this.arr = arr;
        if (heapify)
            heapify();
    }

    protected Heap(Heap<E> heap) {
        this(heap.cap, heap.arr, false);
    }

    private E[] arr;

    @Override
    public boolean push(E e) {
        if (isFull())
            return false;
        heapUp(size++, e, true);
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        E ret = arr[0];
        E bot = arr[--size];
        arr[size] = null;
        heapDown(0, bot, true);
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
            heapDown(0, e, true);
            return peek;
        }
        return e;
    }

    @Override
    protected boolean heapUp(int i) {
        return heapUp(i, arr[i], false);
    }

    @Override
    protected boolean heapDown(int i) {
        return heapDown(i, arr[i], false);
    }

    private boolean heapUp(int start, E e, boolean writeOnStart) {
        int i = start;
        while (i > 0) {
            int up = up(i);
            E top = arr[up];
            if (compare(e, top))
                i = helpHeap(i, up, top);
            else
                break;
        }
        return write(start, e, writeOnStart, i);
    }

    private boolean heapDown(int start, E e, boolean writeOnStart) {
        int i = start;
        int bottom = bottom();
        while (i <= bottom) {
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
        return write(start, e, writeOnStart, i);
    }

    private int helpHeap(int i, int next, E e) {
        arr[i] = e;
        return next;
    }

    private boolean write(int start, E e, boolean writeOnStart, int i) {
        boolean ret = i != start;
        if (ret || writeOnStart)
            arr[i] = e;
        return ret;
    }

    protected abstract boolean compare(E A, E B);

    public abstract Heap<E> clone();

    public static class Min<E extends Comparable<E>> extends Heap<E> {

        public Min(Class<E> Class, int cap, int length) {
            super(Class, cap, length);
        }

        public Min(int cap, E[] arr) {
            super(cap, arr);
        }

        private Min(Min<E> heap) {
            super(heap);
        }

        @Override
        public Min<E> clone() {
            return new Min<E>(this);
        }

        @Override
        protected boolean compare(E A, E B) {
            return A.compareTo(B) < 0;
        }

    }

    public static class Max<E extends Comparable<E>> extends Heap<E> {

        public Max(Class<E> Class, int cap, int length) {
            super(Class, cap, length);
        }

        public Max(int cap, E[] arr) {
            super(cap, arr);
        }

        private Max(Max<E> heap) {
            super(heap);
        }

        @Override
        public Max<E> clone() {
            return new Max<E>(this);
        }

        @Override
        protected boolean compare(E A, E B) {
            return A.compareTo(B) > 0;
        }

    }

    public static class Comp<E> extends Heap<E> {

        public Comp(Class<E> Class, Comparator<E> comp, int cap, int length) {
            super(Class, cap, length);
            this.comp = comp;
        }

        public Comp(Comparator<E> comp, int cap, E[] arr) {
            super(cap, arr, false);
            this.comp = comp;
            heapify();
        }

        private Comp(Comp<E> heap) {
            super(heap);
            this.comp = heap.comp;
        }

        @Override
        public Comp<E> clone() {
            return new Comp<E>(this);
        }

        private Comparator<E> comp;

        @Override
        protected boolean compare(E A, E B) {
            return comp.compare(A, B) < 0;
        }

        @Override
        public void heapify() {
            if (comp != null)
                super.heapify();
        }

    }

}
