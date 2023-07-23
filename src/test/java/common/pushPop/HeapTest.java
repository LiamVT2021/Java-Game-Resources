package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static common.prim.NumStreamTest.assertNumEquals;

import java.util.stream.Stream;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.pushPop.Heap.GenHeap;
import common.prim.PrimHeap.*;

public class HeapTest extends PushPopTest {

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

    static Stream<Heap<? extends Number, Number, ?>> allHeaps() {
        return Stream.concat(minHeaps(), maxHeaps());
    }

    private void fill(Heap<? extends Number, Number, ?> heap) {
        heap.pushAll(3, 7, 9, 2, 11, 13);
    }

    @ParameterizedTest
    @MethodSource("allHeaps")
    public void testPushPop(PushPopArray<? extends Number, Number, ?> pushPop) {
        super.testPushPop(pushPop);
    }

    @ParameterizedTest
    @MethodSource("minHeaps")
    public void testSortMin(Heap<? extends Number, Number, ?> min) {
        fill(min);
        assertEquals(2, min.swap(5).intValue());
        assertEquals(3, min.peek().intValue());
        assertNumEquals(min::empty, 3, 5, 7, 9, 11, 13);
    }

    @ParameterizedTest
    @MethodSource("maxHeaps")
    public void testSortMax(Heap<? extends Number, Number, ?> max) {
        fill(max);
        assertEquals(13, max.swap(5).intValue());
        assertEquals(11, max.peek().intValue());
        assertNumEquals(max::empty, 11, 9, 7, 5, 3, 2);
    }

    @Test
    public void testHeapString() {
        IntHeap min = new IntHeap(arrSize, false);
        assertEquals("", min.heapString());
        fill(min);
        assertEquals("2\n3, 9\n7, 11, 13", min.heapString());
    }

}
