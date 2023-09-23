package common.dataStruct;

import java.util.Iterator;

/**
 * Iterates over an ArrayWrapper
 * 
 * @param <G> the type returned by this array
 * @version 9/22/23
 */
public class ArrayIterator<G> implements Iterator<G> {
    private ArrayWrapper<G, ? super G, ?> array;
    private int i, size;

    public ArrayIterator(ArrayWrapper<G, ? super G, ?> array, int size) {
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
