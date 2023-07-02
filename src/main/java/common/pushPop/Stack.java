package common.pushPop;

import java.util.Iterator;

public abstract class Stack<G extends S, S, A> extends PushPop.Array<G, S, A> {

    protected Stack(ArrayWrapper<G, S, A> array) {
        super(array);
    }

    @Override
    public boolean push(S value) {
        if (value == null || isFull())
            return false;
        array.set(size++, value);
        return true;
    }

    @Override
    public G peek() {
        return isEmpty() ? null : array.get(size - 1);
    }

    @Override
    public G pop() {
        return isEmpty() ? null : array.remove(--size);
    }

    @Override
    public G swap(S value) {
        return isEmpty() ? array.cast(value) : array.swap(size - 1, value);
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
                return array.get(i);
            }

        };
    }

    public static class Gen<V> extends Stack<V, V, V[]> {

        public Gen(V[] array) {
            super(new GenericArray<>(array));
        }

    }

}
