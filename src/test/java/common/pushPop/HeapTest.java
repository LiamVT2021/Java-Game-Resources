package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.prim.PrimHeap;

public class HeapTest {

    private Heap.Gen<Number> numMin;
    private Heap.Gen<Number> numMax;
    private PrimHeap.Byte byteMin;
    private PrimHeap.Byte byteMax;
    private PrimHeap.Short shortMin;
    private PrimHeap.Short shortMax;
    private PrimHeap.Int intMin;
    private PrimHeap.Int intMax;
    private PrimHeap.Long longMin;
    private PrimHeap.Long longMax;
    private PrimHeap.Float floatMin;
    private PrimHeap.Float floatMax;
    private PrimHeap.Double doubleMin;
    private PrimHeap.Double doubleMax;

    private static final int arrSize = 6;
    private static final Comparator<Number> numComp = (a, b) -> a.intValue() - b.intValue();

    @BeforeEach
    private void setUp() {
        numMin = new Heap.Gen<>(new Number[arrSize], false, numComp);
        numMax = new Heap.Gen<>(new Number[arrSize], true, numComp);
        byteMin = new PrimHeap.Byte(arrSize, false);
        byteMax = new PrimHeap.Byte(arrSize, true);
        shortMin = new PrimHeap.Short(arrSize, false);
        shortMax = new PrimHeap.Short(arrSize, true);
        intMin = new PrimHeap.Int(arrSize, false);
        intMax = new PrimHeap.Int(arrSize, true);
        longMin = new PrimHeap.Long(arrSize, false);
        longMax = new PrimHeap.Long(arrSize, true);
        floatMin = new PrimHeap.Float(arrSize, false);
        floatMax = new PrimHeap.Float(arrSize, true);
        doubleMin = new PrimHeap.Double(arrSize, false);
        doubleMax = new PrimHeap.Double(arrSize, true);
    }

    private void forMin(Consumer<Heap<? extends Number, Number, ?>> consumer) {
        Stream.of(numMin, byteMin, shortMin, intMin, longMin, floatMin, doubleMin).forEach(consumer);
    }

    private void forMax(Consumer<Heap<? extends Number, Number, ?>> consumer) {
        Stream.of(numMax, byteMax, shortMax, intMax, longMax, floatMax, doubleMax).forEach(consumer);
    }

    private void forAll(Consumer<Heap<? extends Number, Number, ?>> consumer) {
        forMin(consumer);
        forMax(consumer);
    }

    private void fill() {
        forAll(heap -> heap.pushAll(List.of(3, 7, 9, 2, 11, 13)));
    }

    @Test
    public void testSort() {
        fill();
        forAll(heap -> assertEquals(heap.peek(), heap.swap(5)));
        forMin(min -> assertArrayEquals(new int[] { 3, 5, 7, 9, 11, 13 },
                min.empty().mapToInt(n -> n.intValue()).toArray()));
        forMax(max -> assertArrayEquals(new int[] { 11, 9, 7, 5, 3, 2 },
                max.empty().mapToInt(n -> n.intValue()).toArray()));
    }

    @Test
    public void testEdge() {
        assertEquals("->", numMin.display());
        forAll(heap -> {
            assertFalse(heap.push(null));
            assertNull(heap.peek());
            assertNull(heap.pop());
            assertEquals(5, heap.swap(5).intValue());
        });
        fill();
        assertEquals("-> 2\n3, 9\n7, 11, 13", numMin.display());
        forAll(heap -> assertFalse(heap.push(5)));
    }

}
