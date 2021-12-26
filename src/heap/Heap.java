package heap;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.IntFunction;

public class Heap<E> extends Expand.Array<E> implements HeapADT, PushPop<E>, Cloneable {

    private BiPredicate<E, E> comp;

    public Heap(IntFunction<E[]> newArr, int length, BiPredicate<E, E> comp) {
        super(newArr, length);
        this.comp = comp;
    }

    public Heap(E[] arr, BiPredicate<E, E> comp) {
        super(arr);
        this.comp = comp;
        heapify();
    }

    private Heap(Heap<E> heap) {
        super(heap.array());
        this.comp = heap.comp;
        // this.capacity = heap.capacity;
        // this.min = heap.min;
    }

    @Override
    public boolean isEmpty(){
        return super.isEmpty();
    }

    public static <E> BiPredicate<E, E> min(Comparator<? super E> comp) {
        return (a, b) -> comp.compare(a, b) < 0;
    }

    public static <E> BiPredicate<E, E> max(Comparator<? super E> comp) {
        return (a, b) -> comp.compare(a, b) > 0;
    }

    @Override
    public boolean push(E e) {
        if (!expand())
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
        if (comp.test(peek, e)) {
            heapDown(0, e, true);
            return peek;
        }
        return e;
    }

    @Override
    public boolean heapUp(int i) {
        return heapUp(i, arr[i], false);
    }

    @Override
    public boolean heapDown(int i) {
        return heapDown(i, arr[i], false);
    }

    private boolean heapUp(int start, E e, boolean writeOnStart) {
        int i = start;
        while (i > 0) {
            int up = up(i);
            E top = arr[up];
            if (comp.test(e, top))
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
                if (comp.test(left, e))
                    i = helpHeap(i, l, left);
                else
                    break;
            }
            E right = arr[r];
            if (comp.test(left, e) && !comp.test(right, left))
                i = helpHeap(i, l, left);
            else if (comp.test(right, e))
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
        if (i == start && !writeOnStart)
            return false;
        arr[i] = e;
        return true;
    }

    @Override
    public Heap<E> clone() {
        return new Heap<E>(this);
    }

    @SuppressWarnings("unchecked")
    public Heap<E> lock() {
        return (Heap<E>) super.lock();
    }

    public E[] sorted(IntFunction<E[]> newArr, boolean clone, boolean reverse) {
        E[] ret = newArr.apply(size);
        Heap<E> heap = clone ? clone() : this;
        if (reverse)
            for (int i = ret.length - 1; i >= 0; i--)
                ret[i] = heap.pop();
        else
            for (int i = 0; i < ret.length; i++)
                ret[i] = heap.pop();
        return ret;
    }
}
