package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.prim.PrimHeap;

public class HeapTest {

    private PrimHeap.Int intMin;
    private PrimHeap.Int intMax;

    private static final int arrSize = 6;

    @BeforeEach
    private void setUp() {
        intMin = new PrimHeap.Int(arrSize, false);
        intMax = new PrimHeap.Int(arrSize, true);
    }

    private void forAll(Consumer<Heap<? extends Number, Number, ?>> consumer) {
        Stream.of(intMin, intMax).forEach(consumer);
    }

    private void fill() {
        forAll(heap -> heap.pushAll(List.of(3, 7, 9, 2, 11, 13)));
    }

    @Test
    public void testSort() {
        fill();
        forAll(heap -> assertEquals(heap.peek(), heap.swap(5)));
        assertEquals(List.of(3, 5, 7, 9, 11, 13), intMin.empty().toList());
        assertEquals(List.of(11, 9, 7, 5, 3, 2), intMax.empty().toList());
    }

    @Test
    public void testEdge() {
        forAll(heap -> {
            assertFalse(heap.push(null));
            assertNull(heap.peek());
            assertNull(heap.pop());
            assertEquals(5, heap.swap(5));
        });
        fill();
        forAll(heap -> assertFalse(heap.push(5)));
    }

}
