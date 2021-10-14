package util;

import java.util.Iterator;

public class SubArray<E> implements IterableExt<E> {

    private E[] arr;
    private int[] indexes;

    public SubArray(E[] arr, int... indexes) {
        this.arr = arr;
        this.indexes = indexes;
    }

    public int[] indexes(){
        return indexes;
    }

    public void addIndex(int index) {
        int[] newArr = new int[indexes.length + 1];
        for (int i = 0; i < arr.length; i++)
            newArr[i] = indexes[i];
        indexes[indexes.length] = index;
        indexes = newArr;
    }

    @Override
    public Iterator<E> iterator() {
        return new It();
    }

    private class It implements Iterator<E> {

        private int i;

        @Override
        public boolean hasNext() {
            return i < indexes.length && indexes[i] < arr.length;
        }

        @Override
        public E next() {
            return arr[i++];
        }

    }

}
