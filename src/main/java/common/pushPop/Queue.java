package common.pushPop;

import java.util.Iterator;

public abstract class Queue<G extends S, S, A> extends PushPopArray<G, S, A> {

    private int next;

    protected Queue(ArrayWrapper<G, S, A> array) {
        super(array);
    }

    private int nextIndex(int next) {
        return (next + 1) % capacity();
    }

    @Override
    protected void insert(S value) {
        array.set((next + size++) % capacity(), value);
    }

    @Override
    protected int peekIndex() {
        return next;
    }

    @Override
    protected G remove() {
        G ret = array.remove(next);
        next = nextIndex(next);
        size--;
        return ret;
    }

    @Override
    protected G swapHelp(S value) {
        G ret = remove();
        insert(value);
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

    public static class GenQueue<V> extends Queue<V, V, V[]> {

        public GenQueue(V[] array) {
            super(new GenericArray<>(array));
        }

    }

}
