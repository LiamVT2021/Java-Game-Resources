package heap;

import java.util.Iterator;

import util.Container;

public interface PushPop<E> extends Iterable<E>, Container {
    public boolean push(E e);

    public default boolean pushAll(E[] es) {
        for (E e : es)
            if (!push(e))
                return false;
        return true;
    }

    public default boolean pushAll(Iterable<E> es) {
        for (E e : es)
            if (!push(e))
                return false;
        return true;
    }

    public E pop();

    public E peek();

    public default E swap(E e) {
        if (e == null || isEmpty())
            return e;
        E ret = pop();
        push(e);
        return ret;
    }

    @Override
    public default Iterator<E> iterator() {
        return new PopIt<E>(this);
    }

    public static interface Prim extends PushPop<Integer> {
        @Override
        public default boolean push(Integer i) {
            return i == null ? false : push((int) i);
        }

        public boolean push(int i);

        public default boolean pushAll(int... is) {
            for (int i : is)
                if (!push(i))
                    return false;
            return true;
        }

        @Override
        public default Integer pop() {
            return isEmpty() ? null : primPop();
        }

        public int primPop();

        @Override
        public default Integer peek() {
            return isEmpty() ? null : primPeek();
        }

        public int primPeek();

        public default Integer swap(Integer i) {
            return i == null ? null : swap((int) i);
        }

        public default int swap(int i) {
            if (isEmpty())
                return i;
            int ret = pop();
            push(i);
            return ret;
        }

        @Override
        public default Iterator<Integer> iterator() {
            return new PopIt.Prim(this);
        }

    }

}
