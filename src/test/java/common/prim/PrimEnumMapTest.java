package common.prim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrimEnumMapTest {

    private enum Type {
        A, B, C
    }

    private PrimEnumMap<Type, Integer> map;

    @BeforeEach
    private void setUp() {
        map = new PrimEnumMap.Int<>(Type.class);
    }

    @Test
    public void testSet() {
        map.set(Type.B, 4);
        assertEquals(4, map.get(Type.B));
    }

    @Test
    public void testSwap() {
        assertEquals(0, map.swap(Type.A, 3));
        assertEquals(3, map.get(Type.A));
    }

}
