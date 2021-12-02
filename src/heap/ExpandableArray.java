package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import util.Math;

public abstract class ExpandableArray<ArrType> implements Expandable<ArrType> {

    private ExpandableArray(int size, int min, int capacity, ArrType arr) {
        this.size = size;
        this.min = min;
        this.capacity = capacity;
        this.arr = arr;
    }

    private ExpandableArray(ArrType arr, int size) {
        this(size, -1, -1, arr);
    }

    protected int size;
    private int min;
    private int capacity;
    protected ArrType arr;

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
        arr = arrCopy(arr, newSize);
        return true;
    }

    protected abstract ArrType arrCopy(ArrType array, int length);

    protected static final class Array<E> extends ExpandableArray<E[]> {

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
        protected E[] arrCopy(E[] array, int length) {
            return Arrays.copyOf(array, length);
        }

        protected E[] sorted(Comparator<? super E> comp) {
            E[] ret = array();
            Arrays.sort(ret, comp);
            return ret;
        }

    }

    protected static abstract class Prim<ArrType> extends ExpandableArray<ArrType> {

        private Prim(ArrType arr, int size) {
            super(arr, size);
        }

        protected abstract void set(int index, int value);

        protected abstract int get(int index);

        protected void map(int index, IntUnaryOperator mapper) {
            set(index, mapper.applyAsInt(get(index)));
        }

        protected boolean map(IntUnaryOperator mapper) {
            if (mapper == null)
                return false;
            for (int i = 0; i < size; i++)
                map(i, mapper);
            return true;
        }

    }

    protected static final class Int extends Prim<int[]> {

        protected Int(int length) {
            super(new int[length], 0);
        }

        protected Int(int... arr) {
            super(arr, arr.length);
        }

        @Override
        protected void set(int index, int value) {
            arr[index] = value;
        }

        @Override
        protected int get(int index) {
            return arr[index];
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public int[] arrCopy(int[] array, int length) {
            return Arrays.copyOf(array, length);
        }

    }

    protected static final class Short extends Prim<short[]> {

        protected Short(int length) {
            super(new short[length], 0);
        }

        protected Short(short... arr) {
            super(arr, arr.length);
        }

        @Override
        protected void set(int index, int value) {
            arr[index] = (short) value;
        }

        @Override
        protected int get(int index) {
            return arr[index];
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public short[] arrCopy(short[] array, int length) {
            return Arrays.copyOf(array, length);
        }
    }

    protected static final class Byte extends Prim<byte[]> {

        protected Byte(int length) {
            super(new byte[length], 0);
        }

        protected Byte(byte... arr) {
            super(arr, arr.length);
        }

        @Override
        protected void set(int index, int value) {
            arr[index] = (byte) value;
        }

        @Override
        protected int get(int index) {
            return arr[index];
        }

        protected void setArr(byte... arr) {
            this.arr = arr;
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public byte[] arrCopy(byte[] array, int length) {
            return Arrays.copyOf(array, length);
        }
    }
}
