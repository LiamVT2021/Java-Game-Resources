package common.dataStruct;

import java.util.Iterator;

public class ArrayIterator<G> implements Iterator<G> {
    private ArrayWrapper<G, ?, ?> array;
    private int i, size;

    public ArrayIterator(ArrayWrapper<G, ?, ?> array, int size) {
        this.array = array;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return i < size;
    }

    @Override
    public G next() {
        return array.get(i++);
    }
}
