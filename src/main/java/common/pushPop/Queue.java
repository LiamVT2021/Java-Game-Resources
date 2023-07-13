package common.pushPop;

import java.util.Iterator;

public abstract class Queue<G extends S, S, A> extends PushPop.Array<G, S, A> {

    private int next;

    protected Queue(ArrayWrapper<G, S, A> array) {
        super(array);
    }

    private G next(boolean remove) {
        G ret = remove ? array.remove(next) : array.get(next);
        next = nextIndex(next);
        return ret;
    }

    private int nextIndex(int next) {
        return (next + 1) % array.capacity();
    }

    private void insert(S value) {
        array.set((next + size) % array.capacity(), value);
    }

    @Override
    public boolean push(S value) {
        if (isFull())
            return false;
        insert(value);
        size++;
        return true;
    }

    @Override
    public G peek() {
        return array.get(next);
    }

    @Override
    public G pop() {
        if (isEmpty())
            return null;
        size--;
        return next(true);
    }

    @Override
    public G swap(S value) {
        if (isEmpty())
            return array.cast(value);
        insert(value);
        return next(true);
    }

    public G loop() {
        if (isEmpty())
            return null;
        if (isFull())
            return next(false);
        G ret = next(true);
        insert(ret);
        return ret;
    }

    @Override
    public Iterator<G> iterator() {
        return new Iterator<G>() {

            private int s = 0, i = next;

            @Override
            public boolean hasNext() {
                return s < size;
            }

            @Override
            public G next() {
                G ret = array.get(i);
                s++;
                i = nextIndex(i);
                return ret;
            }

        };
    }

    public static class Gen<V> extends Queue<V, V, V[]> {

        public Gen(V[] array) {
            super(new GenericArray<>(array));
        }

    }

}