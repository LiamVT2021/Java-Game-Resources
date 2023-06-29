package common.pushPop;

import java.util.Comparator;
import java.util.function.BiPredicate;

public abstract class Heap<G extends S, S, A> extends PushPop.Array<G, S, A> {

    private final BiPredicate<S, S> belongsAbove;

    public Heap(ArrayWrapper<G, S, A> array, BiPredicate<S, S> belongsAbove) {
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
    public boolean push(S value) {
        if (value == null || isFull())
            return false;
        int b = size++;
        int t = up(b);
        G cur = array.get(t);
        while (belongsAbove.test(value, cur)) {
            array.set(b, cur);
            if (t == 0) {
                array.set(0, value);
                return true;
            }
            b = t;
            t = up(b);
            cur = array.get(t);
        }
        array.set(b, value);
        return true;
    }

    @Override
    public G peek() {
        return isEmpty() ? null : array.get(0);
    }

    @Override
    public G pop() {
        if (isEmpty())
            return null;
        return heapDown(array.remove(--size));
    }

    @Override
    public G swap(S value) {
        if (isEmpty())
            return array.cast(value);
        return heapDown(value);
    }

    private G heapDown(S value) {
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

    public static class Gen<V> extends Heap<V, V, V[]> {

        public Gen(V[] array, BiPredicate<V, V> belongsAbove) {
            super(new GenericArray<>(array), belongsAbove);
        }

        public Gen(V[] array, boolean max, Comparator<V> comp) {
            this(array, max ? (a, b) -> comp.compare(a, b) > 0 : (a, b) -> comp.compare(a, b) < 0);
        }

    }

}
