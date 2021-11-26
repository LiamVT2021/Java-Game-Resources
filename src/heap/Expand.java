package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

import util.Math;

public abstract class Expand {

    private Expand(int size, int min, int capacity) {
        this.size = size;
        this.min = min;
        this.capacity = capacity;
    }

    private Expand(int size) {
        this(size, -1, -1);
    }

    private Expand() {
        this(0);
    }

    protected int size;
    protected int min;
    protected int capacity;

    public int size() {
        return size;
    }

    public Expand setRange(int min, int capacity) {
        this.min = min;
        this.capacity = capacity;
        return this;
    }

    public Expand setRange(int lock) {
        return setRange(lock, lock);
    }

    public Expand free() {
        return setRange(-1);
    }

    public Expand lock() {
        return setRange(length());
    }

    public abstract int length();

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean isFull() {
        return size == capacity || capacity < 0;
    }

    public boolean trim() {
        return arrCopy(Math.Max.reduce(size, min, 0));
    }

    public boolean expand() {
        if (isFull())
            return false;
        if (size < length())
            return true;
        return arrCopy(Math.min(capacity, newSize()));
    }

    protected int newSize() {
        return size * 2 + 1;
    }

    protected abstract boolean arrCopy(int newSize);

    static abstract class Array<E> extends Expand {

        protected Array(IntFunction<E[]> newArr, int length) {
            super();
            this.arr = newArr.apply(length);
        }

        @SafeVarargs
        protected Array(E... arr) {
            super(arr.length);
            this.arr = arr;
        }

        protected E[] arr;

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        protected boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

        public E[] array() {
            return Arrays.copyOf(arr, size);
        }

        public E[] sorted(Comparator<? super E> comp) {
            E[] ret = array();
            Arrays.sort(ret, comp);
            return ret;
        }
    }

    static abstract class Int extends Expand {

        protected Int(int length) {
            super();
            setArr(new int[length]);
        }

        protected Int(int... arr) {
            super(arr.length);
            setArr(arr);
        }

        protected int[] arr;

        public void setArr(int... arr) {
            this.arr = arr;
        }

        @Override
        public int length() {
            return arr.length;
        }

        protected boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

        public int[] array() {
            return Arrays.copyOf(arr, size);
        }
    }

}
