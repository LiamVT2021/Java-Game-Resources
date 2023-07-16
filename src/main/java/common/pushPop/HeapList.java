package common.pushPop;

import static common.prim.PrimHeap.*;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class HeapList<V> {

    private final Heap<V, ? super V, ?>[] heaps;

    @SafeVarargs
    public HeapList(Heap<V, ? super V, ?>... heaps) {
        this.heaps = heaps;
    }

    public int size() {
        return Stream.of(heaps).mapToInt(Heap::size).sum();
    }

    public int capacity() {
        return Stream.of(heaps).mapToInt(Heap::capacity).sum();
    }

    public boolean isEmpty() {
        return Stream.of(heaps).allMatch(Heap::isEmpty);
    }

    public boolean isFull() {
        return Stream.of(heaps).allMatch(Heap::isFull);
    }

    public boolean push(V value) {
        for (Heap<V, ? super V, ?> heap : heaps) {
            if (heap.isFull())
                continue;
            heap.push(value);
            return true;
        }
        return false;
    }

    public HeapList<V> fill(Supplier<V> supplier) {
        for (Heap<V, ? super V, ?> cur : heaps) {
            while (!cur.isFull()) {
                V value = supplier.get();
                for (Heap<V, ? super V, ?> swap : heaps) {
                    if (cur == swap)
                        break;
                    value = swap.swap(value);
                }
                cur.push(value);
            }
        }
        return this;
    }

    public V swap(V value) {
        for (Heap<V, ? super V, ?> heap : heaps)
            value = heap.swap(value);
        return value;
    }

    ////////////

    public static HeapList<Integer> middleInt(int dis, int adv) {
        return new HeapList<Integer>(new IntHeap(dis, true), new IntHeap(adv, false));
    }
}
