package common.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.prim.PrimHeap;

public class HeapTest {

    private PrimHeap<Integer> intMin;

    @BeforeEach
    private void setUp() {
        intMin = PrimHeap.IntMin(5);
    }

    @Test
    public void testFill() {
        assertEquals(5, intMin.capacity());
        assertTrue(intMin.isEmpty());
        assertFalse(intMin.isFull());
        assertEquals(0, intMin.size());
        intMin.fill(() -> 5);
        assertTrue(intMin.isFull());
        assertFalse(intMin.isEmpty());
        assertEquals(5, intMin.size());
    }
}
