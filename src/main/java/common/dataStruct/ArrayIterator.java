package common.dataStruct;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Spliterator for ArrayWrapper
 * 
 * @param <V> the type returned by this array
 * @version 9/22/23
 */
public class ArrayIterator<V> implements Iterator<V>, Spliterator<V> {

    private final ArrayWrapper<V, ? super V, ?> array;
    private int cur, end;

    public ArrayIterator(ArrayWrapper<V, ? super V, ?> array, int cur, int end) {
        this.array = array;
        this.cur = cur;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        return cur < end;
    }

    @Override
    public V next() {
        return array.get(cur++);
    }

    @Override
    public boolean tryAdvance(Consumer<? super V> action) {
        if (!hasNext())
            return false;
        action.accept(next());
        return true;
    }

    @Override
    public Spliterator<V> trySplit() {
        int size = (int) estimateSize();
        if (size < 2)
            return null;
        int mid = end - size / 2;
        Spliterator<V> ret = new ArrayIterator<>(array, cur, mid);
        cur = mid;
        return ret;
    }

    @Override
    public long estimateSize() {
        return end - cur;
    }

    @Override
    public int characteristics() {
        return ORDERED | SIZED | SUBSIZED;
    }

    @Override
    public void forEachRemaining(Consumer<? super V> action) {
        Iterator.super.forEachRemaining(action);
    }

}
