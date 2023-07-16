package common.pushPop;

import java.util.Comparator;
import java.util.function.BiPredicate;

/**
 * A PushPop data structure that maintains a top element by bianary tree
 * sorting.
 * 
 * @param G the type returned by get methods
 * @param S the type consumed by set methods
 * @param A the type of the wrapped array
 * @version 7/15/23
 */
public abstract class Heap<G extends S, S, A> extends PushPop.Array<G, S, A> {

    private final BiPredicate<S, S> belongsAbove;

    protected Heap(ArrayWrapper<G, S, A> array, BiPredicate<S, S> belongsAbove) {
        super(array);
        this.belongsAbove = belongsAbove;
    }

    private int up(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    @Override
    public void insert(S value) {
        int b = size++;
        if (b > 0) {
            int t = up(b);
            G cur = array.get(t);
            while (belongsAbove.test(value, cur)) {
                array.set(b, cur);
                if (t == 0) {
                    array.set(0, value);
                    return;
                }
                b = t;
                t = up(b);
                cur = array.get(t);
            }
        }
        array.set(b, value);
    }

    @Override
    public int peekIndex() {
        return 0;
    }

    /**
     * Removes the top element from the Heap.
     */
    @Override
    public G remove() {
        return size == 1 ? array.remove(--size) : swapHelp(array.remove(--size));
    }

    @Override
    protected G swapHelp(S value) {
        G ret = array.get(0);
        int t = 0;
        while (true) {
            int l = leftChild(t);
            if (l < size) {
                G left = array.get(l);
                int r = l + 1;
                if (r < size) {
                    G right = array.get(r);
                    if (belongsAbove.test(right, value)) {
                        if (belongsAbove.test(left, right)) {
                            array.set(t, left);
                            t = l;
                        } else {
                            array.set(t, right);
                            t = r;
                        }
                        continue;
                    }
                }
                if (belongsAbove.test(left, value)) {
                    array.set(t, left);
                    t = l;
                    continue;
                }
            }
            array.set(t, value);
            return ret;
        }
    }

    public String heapString() {
        return heapString(", ", "\n");
    }

    public String heapString(CharSequence delim, CharSequence rowDelim) {
        if (isEmpty())
            return "";
        StringBuilder str = new StringBuilder(array.get(0).toString());
        int row = 1;
        for (int i = 1; i < size; i++) {
            if (i == row) {
                row = leftChild(row);
                str.append(rowDelim);
            } else
                str.append(delim);
            str.append(array.get(i));
        }
        return str.toString();
    }

    public static class GenHeap<V> extends Heap<V, V, V[]> {

        public GenHeap(V[] array, BiPredicate<V, V> belongsAbove) {
            super(new GenericArray<>(array), belongsAbove);
        }

        public GenHeap(V[] array, boolean max, Comparator<V> comp) {
            this(array, max ? (a, b) -> comp.compare(a, b) > 0 : (a, b) -> comp.compare(a, b) < 0);
        }

    }

}
