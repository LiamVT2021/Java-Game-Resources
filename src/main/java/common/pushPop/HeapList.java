package common.pushPop;

import static common.prim.PrimHeap.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

import common.util.StringUtils;

public abstract class HeapList<G extends S, S> {

    private final Heap<G, S, ?>[] heaps;

    @SafeVarargs
    protected HeapList(Heap<G, S, ?>... heaps) {
        this.heaps = heaps;
        if (isFull())
            throw new IllegalArgumentException("Must have some capacity");
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

    public boolean push(S value) {
        for (Heap<G, S, ?> heap : heaps) {
            if (!heap.isFull()) {
                heap.push(value);
                return true;
            }
            value = heap.swap(value);
        }
        return false;
    }

    public HeapList<G, S> fill(Supplier<S> supplier) {
        for (Heap<G, S, ?> cur : heaps) {
            while (!cur.isFull()) {
                S value = supplier.get();
                for (Heap<G, S, ?> swap : heaps) {
                    if (cur == swap)
                        break;
                    value = swap.swap(value);
                }
                cur.push(value);
            }
        }
        return this;
    }

    public G swap(S value) {
        Iterator<Heap<G, S, ?>> it = new GenericArray<>(heaps).iterator();
        G ret = it.next().swap(value);
        while (it.hasNext())
            ret = it.next().swap(ret);
        return ret;
    }

    public String toString() {
        return StringUtils.join("\n\n", Stream.of(heaps).map(Heap::toString));
    }

    ////////////

    public static class GenHeapList<V> extends HeapList<V, V> {
        @SafeVarargs
        public GenHeapList(Heap<V, V, ?>... heaps) {
            super(heaps);
        }
    }

    public static <V> GenHeapList<V> middleGen(V[] dis, V[] adv, Comparator<V> comp) {
        return new GenHeapList<>(new GenHeap<>(dis, true, comp), new GenHeap<>(adv, false, comp));
    }

}
