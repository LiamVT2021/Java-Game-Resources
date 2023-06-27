package common.heap;

import java.util.Comparator;
import java.util.function.BiPredicate;

public class Heap<V> extends HeapADT<V> {

    private final V[] array;
    private final BiPredicate<V, V> belongsAbove;

    public Heap(V[] array, BiPredicate<V, V> belongsAbove) {
        this.array = array;
        this.belongsAbove = belongsAbove;
    }

    @Override
    public V get(int index) {
        return array[index];
    }

    @Override
    protected void set(int index, V value) {
        array[index] = value;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    protected boolean belongsAbove(V a, V b) {
        return belongsAbove.test(a, b);
    }

    public static <V> Heap<V> minHeap(V[] array, Comparator<V> comp) {
        return new Heap<>(array, (a, b) -> comp.compare(a, b) < 0);
    }

    public static <V> Heap<V> maxHeap(V[] array, Comparator<V> comp) {
        return new Heap<>(array, (a, b) -> comp.compare(a, b) > 0);
    }

}
