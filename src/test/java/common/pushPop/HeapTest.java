package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static common.pushPop.Heap.GenHeap;
import static common.prim.PrimHeap.*;

import java.util.stream.Stream;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.prim.NumStream;

public class HeapTest {

    private static final int arrSize = 6;
    static final Comparator<Number> numComp = (a, b) -> a.intValue() - b.intValue();

    private static Stream<Heap<? extends Number, Number, ?>> heaps(boolean max) {
        return Stream.of(new GenHeap<>(new Number[arrSize], max, numComp),
                new ByteHeap(arrSize, max), new ShortHeap(arrSize, max), new IntHeap(arrSize, max),
                new LongHeap(arrSize, max), new FloatHeap(arrSize, max), new DoubleHeap(arrSize, max));
    }

    static Stream<Heap<? extends Number, Number, ?>> minHeaps() {
        return heaps(false);
    }

    static Stream<Heap<? extends Number, Number, ?>> maxHeaps() {
        return heaps(true);
    }

    private void fill(Heap<? extends Number, Number, ?> heap) {
        heap.pushAll(3, 7, 9, 2, 11, 13);
    }

    @ParameterizedTest
    @MethodSource("minHeaps")
    public void testSortMin(Heap<? extends Number, Number, ?> min) {
        fill(min);
        assertEquals(2, min.swap(5).intValue());
        assertEquals(3, min.peek().intValue());
        assertArrayEquals(new int[] { 3, 5, 7, 9, 11, 13 }, ((NumStream) min::empty).intArr());
    }

    @ParameterizedTest
    @MethodSource("maxHeaps")
    public void testSortMax(Heap<? extends Number, Number, ?> max) {
        fill(max);
        assertEquals(13, max.swap(5).intValue());
        assertEquals(11, max.peek().intValue());
        assertArrayEquals(new int[] { 11, 9, 7, 5, 3, 2 }, ((NumStream) max::empty).intArr());
    }

    @Test
    public void testHeapString() {
        IntHeap min = new IntHeap(arrSize, false);
        assertEquals("", min.heapString());
        fill(min);
        assertEquals("2\n3, 9\n7, 11, 13", min.heapString());
    }

}
