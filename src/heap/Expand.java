package heap;

import java.util.Arrays;

import util.Math;

public abstract class Expand {

    private Expand(int size, int min, int capacity) {
        this.size = size;
        this.min = min;
        this.capacity = capacity;
    }

    private Expand(int size, int bound, boolean expand) {
        this(size, expand ? -1 : bound, expand ? -1 : bound);
    }

    protected int size;
    protected int min;
    protected int capacity;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean isFull() {
        return size == capacity || capacity < 0;
    }

    public boolean trim() {
        return arrCopy(Math.Max.reduce(size, min, 0));
    }

    public boolean expand(int newSize) {
        return (capacity >= 0 && (newSize <= size || newSize > capacity)) ? false : arrCopy(newSize);
    }

    protected abstract boolean arrCopy(int newSize);

    static abstract class Array<E> extends Expand {

        Array(E[] arr, int min, int capacity) {
            super(arr.length, min, capacity);
            this.arr = arr;
        }

        Array(E[] arr, boolean expand) {
            super(arr.length, arr.length, expand);
            this.arr = arr;
        }

        Array(Class<E> clazz, int start, int min, int capacity) {
            super(0, min, capacity);
            arr = newArr(clazz, start);
        }

        Array(Class<E> clazz, int start, boolean expand) {
            super(0, start, expand);
            arr = newArr(clazz, start);
        }

        @SuppressWarnings("unchecked")
        private E[] newArr(Class<E> clazz, int size) {
            return (E[]) java.lang.reflect.Array.newInstance(clazz, size);
        }

        protected E[] arr;

        @Override
        protected boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

        public E[] cloneArr(boolean clone) {
            return clone ? Arrays.copyOf(arr, arr.length) : arr;
        }
    }

    static abstract class Int extends Expand {

        Int(int[] arr, int min, int capacity) {
            super(arr.length, min, capacity);
            this.arr = arr;
        }

        Int(int[] arr, boolean expand) {
            super(arr.length, arr.length, expand);
            this.arr = arr;
        }

        Int(int start, int min, int capacity) {
            super(0, min, capacity);
            arr = new int[start];
        }

        Int(int start, boolean expand) {
            super(0, start, expand);
            arr = new int[start];
        }

        private int[] arr;

        protected boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

        public int[] array(boolean clone) {
            return clone ? Arrays.copyOf(arr, arr.length) : arr;
        }
    }

}
