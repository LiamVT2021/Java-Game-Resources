package common.pushPop;

import java.util.Iterator;

public abstract class Stack<G extends S, S, A> extends PushPopArray<G, S, A> {

    protected Stack(ArrayWrapper<G, S, A> array) {
        super(array);
    }

    @Override
    public int peekIndex() {
        return size - 1;
    }

    @Override
    public G remove() {
        return array.remove(--size);
    }

    @Override
    protected G swapHelp(S value) {
        return array.swap(size - 1, value);
    }

    @Override
    public Iterator<G> iterator() {
        return new Iterator<G>() {

            private int i = size - 1;

            @Override
            public boolean hasNext() {
                return i >= 0;
            }

            @Override
            public G next() {
                return array.get(i--);
            }

        };
    }

    public static class GenStack<V> extends Stack<V, V, V[]> {

        public GenStack(V[] array) {
            super(new GenericArray<>(array));
        }

    }

}
