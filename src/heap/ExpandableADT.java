package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import util.Math;

public abstract class ExpandableADT<ArrType> implements Expandable<ArrType> {

    private ExpandableADT(int size, int min, int capacity) {
        this.size = size;
        this.min = min;
        this.capacity = capacity;
    }

    private ExpandableADT(int size) {
        this(size, -1, -1);
    }

    private ExpandableADT() {
        this(0);
    }

    protected int size;
    private int min;
    private int capacity;

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

    protected abstract boolean arrCopy(int newSize);

    protected abstract void setArr(ArrType arr);

    protected static class Array<E> extends ExpandableADT<E[]> {

        protected Array(IntFunction<E[]> newArr, int length) {
            super();
            setArr(newArr.apply(length));
        }

        @SafeVarargs
        protected Array(E... arr) {
            super(arr.length);
            setArr(arr);
        }

        private E[] arr;

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public E[] array() {
            return Arrays.copyOf(arr, size);
        }

        @Override
        protected void setArr(E[] arr) {
            this.arr = arr;
        }

        protected E[] sorted(Comparator<? super E> comp) {
            E[] ret = array();
            Arrays.sort(ret, comp);
            return ret;
        }

        @Override
        protected boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

    }

    protected static abstract class Prim<ArrType> extends ExpandableADT<ArrType> {

        private Prim() {
            super();
        }

        private Prim(int size) {
            super(size);
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

    protected static class Int extends Prim<int[]> {

        protected Int(int length) {
            super();
            setArr(new int[length]);
        }

        protected Int(int... arr) {
            super(arr.length);
            setArr(arr);
        }

        private int[] arr;

        @Override
        protected void set(int index, int value) {
            arr[index] = value;
        }

        @Override
        protected int get(int index) {
            return arr[index];
        }

        @Override
        protected void setArr(int[] arr) {
            this.arr = arr;
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

        @Override
        public int[] array() {
            return Arrays.copyOf(arr, size);
        }
    }

    protected static class Short extends Prim<short[]> {

        protected Short(int length) {
            super();
            setArr(new short[length]);
        }

        protected Short(short... arr) {
            super(arr.length);
            setArr(arr);
        }

        private short[] arr;

        @Override
        protected void set(int index, int value) {
            arr[index] = (short) value;
        }

        @Override
        protected int get(int index) {
            return arr[index];
        }

        protected void setArr(short... arr) {
            this.arr = arr;
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

        @Override
        public short[] array() {
            return Arrays.copyOf(arr, size);
        }
    }

    protected static class Byte extends Prim<byte[]> {

        protected Byte(int length) {
            super();
            setArr(new byte[length]);
        }

        protected Byte(byte... arr) {
            super(arr.length);
            setArr(arr);
        }

        private byte[] arr;

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
        public boolean arrCopy(int newSize) {
            if (newSize == arr.length)
                return false;
            arr = Arrays.copyOf(arr, newSize);
            return true;
        }

        public byte[] array() {
            return Arrays.copyOf(arr, size);
        }
    }
}
