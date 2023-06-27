package common.heap;

import java.util.function.Supplier;
import java.util.stream.Stream;

import common.prim.PrimHeap;

public class HeapList<V> {

    private final HeapADT<V>[] heaps;

    @SafeVarargs
    public HeapList(HeapADT<V>... heaps) {
        this.heaps = heaps;
    }

    public int size() {
        return Stream.of(heaps).mapToInt(HeapADT::size).sum();
    }

    public int capacity() {
        return Stream.of(heaps).mapToInt(HeapADT::capacity).sum();
    }

    public boolean isEmpty() {
        return Stream.of(heaps).allMatch(HeapADT::isEmpty);
    }

    public boolean isFull() {
        return Stream.of(heaps).allMatch(HeapADT::isFull);
    }

    public boolean push(V value) {
        for (HeapADT<V> heap : heaps) {
            if (heap.isFull())
                continue;
            heap.push(value);
            return true;
        }
        return false;
    }

    public HeapList<V> fill(Supplier<V> supplier) {
        for (HeapADT<V> cur : heaps) {
            while (!cur.isFull()) {
                V value = supplier.get();
                for (HeapADT<V> swap : heaps) {
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
        for (HeapADT<V> heap : heaps)
            value = heap.swap(value);
        return value;
    }

    ////////////

    // public static HeapList<Integer> middleInt(int dis, int adv) {
    //     return new HeapList<Integer>(new PrimHeap.IntMax(dis), new PrimHeap.IntMin(adv));
    // }
}
