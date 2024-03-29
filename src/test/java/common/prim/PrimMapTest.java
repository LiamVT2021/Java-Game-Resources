package common.prim;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static common.prim.PrimEnumMap.*;

/**
 * Tests all the methods in the PrimMap interface using PrimEnumMap.
 * 
 * @version 7/12/23
 */
public class PrimMapTest {

    private static enum Type {
        A, B, C
    }

    private static Stream<PrimMap<Type, ? extends Number, ?>> allMaps() {
        return Stream.of(new ByteEnumMap<>(Type.class), new ShortEnumMap<>(Type.class),
                new IntEnumMap<>(Type.class), new LongEnumMap<>(Type.class),
                new FloatEnumMap<>(Type.class), new DoubleEnumMap<>(Type.class));
    }

    @ParameterizedTest
    @MethodSource("allMaps")
    public void testGet(PrimEnumMap<Type, Number, ?> map) {
        assertArrayEquals(new int[] { 0, 0 }, map.get(Type.A, Type.B).mapToInt(Number::intValue).toArray());
    }

    @ParameterizedTest
    @MethodSource("allMaps")
    public void testSet(PrimEnumMap<Type, Number, ?> map) {
        map.set(Type.B, 4);
        assertEquals(4, map.get(Type.B).intValue());
    }

    @ParameterizedTest
    @MethodSource("allMaps")
    public void testSwap(PrimEnumMap<Type, Number, ?> map) {
        assertEquals(0, map.swap(Type.A, 3).intValue());
        assertEquals(3, map.get(Type.A).intValue());
    }

    @ParameterizedTest
    @MethodSource("allMaps")
    public void testMap(PrimEnumMap<Type, Number, ?> map) {
        assertEquals("A -> 0", map.map((k, v) -> k + " -> " + v.intValue(), Type.A).findFirst().get());
        assertEquals((map.storesFloat() ? "A: 0.0\nB: 0.0\nC: 0.0" : "A: 0\nB: 0\nC: 0"), map.mapString(Type.values()));
    }

}
