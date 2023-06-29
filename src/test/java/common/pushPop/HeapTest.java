package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.prim.PrimHeap;

public class HeapTest {

    private PrimHeap.Int intMin;
    private PrimHeap.Int intMax;

    @BeforeEach
    private void setUp() {
        intMin = new PrimHeap.Int(5, false);
        intMax = new PrimHeap.Int(5, true);
    }

    private void forAll(Consumer<Heap<? extends Number, Number, ?>> consumer) {
        Stream.of(intMin, intMax).forEach(consumer);
    }

    @Test
    public void testSort() {
        forAll(heap -> heap.pushAll(List.of(3, 7, 9, 2, 5)));
        assertEquals(List.of(2, 3, 5, 7, 9), intMin.empty().toList());
        assertEquals(List.of(9, 7, 5, 3, 2), intMax.empty().toList());
    }

}
