package old.enums;

import java.util.Arrays;

import old.util.RangeArray;
import old.util.SubArray;

public interface EnumArr<E extends Enum<E>> {

    public int get(E key);

    public default void mod(E key, int mod) {
        mod(key.ordinal(), mod);
    }

    public void mod(int index, int mod);

    public default void mod(Iterable<E> keys, int mod) {
        if (keys instanceof SubArray<E>) {
            mod(((SubArray<E>) keys).indexes(), mod);
        } else if (keys instanceof RangeArray<E>) {
            RangeArray<E> range = ((RangeArray<E>) keys);
            mod(range.start(), range.cut(), mod);
        } else {
            for (E key : keys)
                mod(key, mod);
        }
    }

    private void mod(int[] indexArr, int mod) {
        for (int i : indexArr)
            mod(i, mod);
    }

    private void mod(int start, int cut, int mod) {
        for (int i = start; i < cut; i++)
            mod(i, mod);
    }

    public static class Int<E extends Enum<E>> implements EnumArr<E> {
        private int[] arr;

        public Int(int size, int defVal) {
            arr = new int[size];
            Arrays.fill(arr, defVal);
        }

        @Override
        public int get(E key) {
            return arr[key.ordinal()];
        }

        @Override
        public void mod(int index, int mod) {
            arr[index] += mod;
        }
    }

    public static class Short<E extends Enum<E>> implements EnumArr<E> {
        private short[] arr;

        public Short(int size, short defVal) {
            arr = new short[size];
            Arrays.fill(arr, defVal);
        }

        @Override
        public int get(E key) {
            return arr[key.ordinal()];
        }

        @Override
        public void mod(int index, int mod) {
            arr[index] += mod;
        }
    }

}
