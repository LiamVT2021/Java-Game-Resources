package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import util.Math;

public interface Expand {

    public int size();

    public int mmSize();

    public int sizeMM();

    public int sizePP();

    public Expand setRange(int min, int capacity);

    public default Expand setRange(int lock) {
        return setRange(lock, lock);
    }

    public default Expand free() {
        return setRange(-1);
    }

    public default Expand lock() {
        return setRange(length());
    }

    public int length();

    public int capacity();

    public default boolean isEmpty() {
        return size() <= 0;
    }

    public default boolean isFull() {
        return size() >= capacity() && capacity() >= 0;
    }

    public default boolean isOver() {
        return size() > capacity() && capacity() >= 0;
    }

    public default boolean hasRoom() {
        return size() < capacity() || capacity() < 0;
    }

    public default boolean hasRoom(int ex) {
        return size() + ex <= capacity() || capacity() < 0;
    }

    public boolean trim();

    public boolean expand();

    public boolean arrCopy(int newSize);

    public abstract class ADT implements Expand {

        private ADT(int size, int min, int capacity) {
            this.size = size;
            this.min = min;
            this.capacity = capacity;
        }

        private ADT(int size) {
            this(size, -1, -1);
        }

        private ADT() {
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
        public int mmSize() {
            return --size;
        }

        @Override
        public int sizeMM() {
            return size--;
        }

        @Override
        public int sizePP() {
            return size++;
        }

        @Override
        public int capacity() {
            return capacity;
        }

        @Override
        public Expand setRange(int min, int capacity) {
            this.min = min;
            this.capacity = capacity;
            return this;
        }

        @Override
        public boolean trim() {
            return arrCopy(Math.Max.reduce(size, min, 0));
        }

        @Override
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
    }

    static abstract class Array<E> extends ADT {

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
        public boolean arrCopy(int newSize) {
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

    static abstract class Prim extends ADT {

        private Prim() {
            super();
        }

        private Prim(int size) {
            super(size);
        }

        public abstract void set(int index, int value);

        public abstract int get(int index);

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

    static abstract class Int extends Prim {

        protected Int(int length) {
            super();
            setArr(new int[length]);
        }

        protected Int(int... arr) {
            super(arr.length);
            setArr(arr);
        }

        protected int[] arr;

        @Override
        public void set(int index, int value) {
            arr[index] = value;
        }

        @Override
        public int get(int index) {
            return arr[index];
        }

        public void setArr(int... arr) {
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

        public int[] array() {
            return Arrays.copyOf(arr, size);
        }
    }

    static abstract class Short extends Prim {

        protected Short(int length) {
            super();
            setArr(new short[length]);
        }

        protected Short(short... arr) {
            super(arr.length);
            setArr(arr);
        }

        protected short[] arr;

        @Override
        public void set(int index, int value) {
            arr[index] = (short) value;
        }

        @Override
        public int get(int index) {
            return arr[index];
        }

        public void setArr(short... arr) {
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

        public short[] array() {
            return Arrays.copyOf(arr, size);
        }
    }

    static abstract class Byte extends Prim {

        protected Byte(int length) {
            super();
            setArr(new byte[length]);
        }

        protected Byte(byte... arr) {
            super(arr.length);
            setArr(arr);
        }

        protected byte[] arr;

        @Override
        public void set(int index, int value) {
            arr[index] = (byte) value;
        }

        @Override
        public int get(int index) {
            return arr[index];
        }

        public void setArr(byte... arr) {
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
