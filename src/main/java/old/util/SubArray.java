package old.util;

import java.util.Iterator;

public class SubArray<E> implements IterableExt<E> {

    private E[] arr;
    private int[] indexes;

    public SubArray(E[] arr, int... indexes) {
        this.arr = arr;
        this.indexes = indexes;
    }

    public int[] indexes() {
        return indexes;
    }

    public static int[] addIndex(int[] indexes, int index) {
        int[] newArr = new int[indexes.length + 1];
        for (int i = 0; i < indexes.length; i++)
            newArr[i] = indexes[i];
        indexes[indexes.length] = index;
        return newArr;
    }

    public void addIndex(int index) {
        indexes = addIndex(indexes, index);
    }

    @Override
    public Iterator<E> iterator() {
        return new It();
    }

    private class It implements Iterator<E> {

        private int i;

        private It() {
            if (isNull())
                next();
        }

        @Override
        public boolean hasNext() {
            return i < indexes.length && indexes[i] < arr.length;
        }

        @Override
        public E next() {
            E ret = arr[indexes[i++]];
            while (isNull()) // screens null elements
                i++;
            return ret;
        }

        private boolean isNull() {
            return hasNext() && arr[indexes[i]] == null;
        }
    }

}
