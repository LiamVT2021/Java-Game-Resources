package heap;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public abstract class PrimArray<ArrType> {

    protected ArrType arr;

    public abstract void set(int index, int value);

    public abstract int get(int index);

    public void move(int source, int dest) {
        set(dest, get(source));
    }

    public void map(int index, IntUnaryOperator mapper) {
        set(index, mapper.applyAsInt(get(index)));
    }

    public abstract int length();

    public abstract ArrType arrCopy(ArrType array, int length);

    public abstract ArrType newArr(int length);

    public void setArr(ArrType array) {
        arr = array;
    }

    public static class Int extends PrimArray<int[]> {

        public Int(int[] array) {
            setArr(array);
        }

        public Int(int length) {
            this(new int[length]);
        }

        @Override
        public void set(int index, int value) {
            arr[index] = value;
        }

        @Override
        public int get(int index) {
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

        @Override
        public int[] newArr(int length) {
            return new int[length];
        }
    }

    public static class Short extends PrimArray<short[]> {

        public Short(short[] array) {
            setArr(array);
        }

        public Short(int length) {
            this(new short[length]);
        }

        @Override
        public void set(int index, int value) {
            arr[index] = (short) value;
        }

        @Override
        public int get(int index) {
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

        @Override
        public short[] newArr(int length) {
            return new short[length];
        }
    }

    public static class Byte extends PrimArray<byte[]> {

        public Byte(byte[] array) {
            setArr(array);
        }

        public Byte(int length) {
            this(new byte[length]);
        }

        @Override
        public void set(int index, int value) {
            arr[index] = (byte) value;
        }

        @Override
        public int get(int index) {
            return arr[index];
        }

        @Override
        public int length() {
            return arr.length;
        }

        @Override
        public byte[] arrCopy(byte[] array, int length) {
            return Arrays.copyOf(array, length);
        }

        @Override
        public byte[] newArr(int length) {
            return new byte[length];
        }
    }
}
