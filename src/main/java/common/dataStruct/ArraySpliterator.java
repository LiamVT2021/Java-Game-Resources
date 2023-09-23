package common.dataStruct;

import java.util.Spliterator;
import java.util.function.Consumer;

public class ArraySpliterator<V> implements Spliterator<V> {

    private final ArrayWrapper<V, ? super V, ?> array;
    private int cur, end;

    public ArraySpliterator(ArrayWrapper<V, ? super V, ?> array, int cur, int end) {
        this.array = array;
        this.cur = cur;
        this.end = end;
    }

    @Override
    public boolean tryAdvance(Consumer<? super V> action) {
        if (cur >= end)
            return false;
        action.accept(array.get(cur++));
        return true;
    }

    @Override
    public Spliterator<V> trySplit() {
        int size = (int) estimateSize();
        if (size < 2)
            return null;
        int mid = end - size / 2;
        Spliterator<V> ret = new ArraySpliterator<>(array, cur, mid);
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

}
