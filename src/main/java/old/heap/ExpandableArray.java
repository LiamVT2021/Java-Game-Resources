package old.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import old.util.Math;

public abstract class ExpandableArray<Store, ArrType> implements Expandable<ArrType> {

    private ExpandableArray(int size, int min, int capacity, Store arr) {
        this.size = size;
        this.min = min;
        this.capacity = capacity;
        this.arr = arr;
    }

    private ExpandableArray(Store arr, int size) {
        this(size, -1, -1, arr);
    }

    protected int size;
    private int min;
    private int capacity;
    protected Store arr;

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean setLength(int length) {
        if (length == length())
            return true;
        if (length < min || length > capacity)
            return false;
        return arrCopy(length);
    }

    @Override
    public Expandable<ArrType> setRange(int min, int capacity) {
        this.min = min;
        this.capacity = capacity;
        return this;
    }

    @Override
    public boolean trim() {
        return arrCopy(Math.Max.reduce(size, min, 0));
    }

    protected boolean expand() {
        if (isFull())
            return false;
        if (size < length())
            return true;
        return arrCopy(Math.min(capacity, newSize()));
    }

    private int newSize() {
        return size * 2 + 1;
    }

    @Override
    public ArrType array() {
        return arrCopy(arr, size);
    }

    protected boolean arrCopy(int newSize) {
        if (newSize == length())
            return false;
        setArr(arrCopy(arr, newSize));
        return true;
    }

    protected abstract void setArr(ArrType array);

    protected abstract ArrType arrCopy(Store array, int length);

    protected static class Array<E> extends ExpandableArray<E[], E[]> {

        protected Array(IntFunction<E[]> newArr, int length) {
            super(newArr.apply(length), 0);
        }

        @SafeVarargs
        protected Array(E... arr) {
            super(arr, arr.length);
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        protected void setArr(E[] array) {
            arr = array;
        }

        @Override
        protected E[] arrCopy(E[] array, int length) {
            return Arrays.copyOf(array, length);
        }

        protected E[] sorted(Comparator<? super E> comp) {
            E[] ret = array();
            Arrays.sort(ret, comp);
            return ret;
        }

    }

    protected static class Prim<P extends PrimArray<ArrType>, ArrType> extends ExpandableArray<P, ArrType> {

        protected Prim(P primArr) {
            super(primArr, primArr.length());
        }

        protected Prim(P primArr, int size) {
            super(primArr, size);
        }

        @Override
        public int length() {
            return arr.length();
        }

        @Override
        protected void setArr(ArrType array) {
            arr.setArr(array);
        }

        @Override
        protected ArrType arrCopy(P array, int length) {
            return array.arrCopy(array.arr, length);
        }

        protected boolean map(IntUnaryOperator mapper) {
            if (mapper == null)
                return false;
            for (int i = 0; i < size; i++)
                arr.map(i, mapper);
            return true;
        }
    }
}
