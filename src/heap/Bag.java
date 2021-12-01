package heap;

import java.util.function.IntFunction;

import util.Math;

public class Bag<E> extends Expand.Array<E> implements PushPop<E> {

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public Bag(IntFunction<E[]> newArr, int length) {
        super(newArr, length);
    }

    @SafeVarargs
    public Bag(E... arr) {
        super(arr);
    }

    @Override
    public boolean push(E e) {
        if (e == null || !expand())
            return false;
        arr[size++] = e;
        return true;
    }

    private int randInt() {
        return Math.random.nextInt(size);
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        int rand = randInt();
        E ret = arr[rand];
        arr[rand] = arr[--size];
        arr[size] = null;
        return ret;
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return arr[randInt()];
    }

    public static class Int extends Expand.Int implements PushPop.Prim {
        
        public boolean isEmpty() {
            return super.isEmpty();
        }

        public Int(int length) {
            super(length);
        }

        public Int(int... arr) {
            super(arr);
        }

        @Override
        public boolean push(int i) {
            if (!expand())
                return false;
            arr[size++] = i;
            return true;
        }

        private int randInt() {
            return Math.random.nextInt(size);
        }

        @Override
        public int primPop() {
            int rand = randInt();
            int ret = arr[rand];
            arr[rand] = arr[--size];
            return ret;
        }

        @Override
        public int primPeek() {
            return arr[randInt()];
        }

    }

}
