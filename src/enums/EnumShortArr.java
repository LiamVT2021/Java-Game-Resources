package enums;

import java.util.Arrays;

import util.RangeArray;
import util.SubArray;

public abstract class EnumShortArr<E extends Enum<E>> {

    private short[] arr;

    public EnumShortArr(int size, short defVal) {
        arr = new short[size];
        Arrays.fill(arr, defVal);
    }

    public short get(E key) {
        return arr[key.ordinal()];
    }

    public void mod(E key, short mod) {
        arr[key.ordinal()] += mod;
    }

    public void mod(Iterable<E> keys, short mod) {
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

    private void mod(int[] indexArr, short mod) {
        for (int i : indexArr)
            arr[i] += mod;
    }

    private void mod(int start, int cut, short mod) {
        for (int i = start; i < cut; i++)
            arr[i] += mod;
    }

}
