package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.prim.array.*;

/**
 * Tests all methods for Primitive and Generic ArrayWrappers.
 * 
 * @version 7/11/23
 */
public class ArrayWrapperTest {

    private static final int arrSize = 5;

    private static Stream<ArrayWrapper<? extends Number, Number, ?>> allArrays() {
        return Stream.of(new GenericArray<>(new Number[] { 0, 0, 0, 0, 0 }),
                new ByteArray(arrSize), new ShortArray(arrSize), new IntArray(arrSize), new LongArray(arrSize),
                new FloatArray(arrSize), new DoubleArray(arrSize));
    }

    @ParameterizedTest
    @MethodSource("allArrays")
    public void testCapacity(ArrayWrapper<? extends Number, Number, ?> array) {
        assertEquals(arrSize, array.capacity());
    }

    @ParameterizedTest
    @MethodSource("allArrays")
    public void testGet(ArrayWrapper<? extends Number, Number, ?> array) {
        assertEquals(0, array.get(0).intValue());
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(arrSize));
    }

    @ParameterizedTest
    @MethodSource("allArrays")
    public void testSwap(ArrayWrapper<? extends Number, Number, ?> array) {
        Number n = 2;
        assertEquals(0, array.swap(3, n).intValue());
        assertEquals(n, array.remove(3).intValue());
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(arrSize, n));
    }

    @ParameterizedTest
    @MethodSource("allArrays")
    public void testToString(ArrayWrapper<? extends Number, Number, ?> array) {
        assertEquals(array instanceof PrimArray && ((PrimArray<?, ?>) array).storesFloat()
                ? "[ 0.0, 0.0, 0.0, 0.0, 0.0 ]"
                : "[ 0, 0, 0, 0, 0 ]",
                array.toString());
    }

    @Test
    public void testPrimStream() {
        new IntArray(arrSize).intStream().forEach(i -> assertEquals(0, i));
        new LongArray(arrSize).longStream().forEach(i -> assertEquals(0, i));
        new DoubleArray(arrSize).doubleStream().forEach(i -> assertEquals(0, i));
    }

    @Test
    public void testGen() {
        GenericArray<Number> numArr = new GenericArray<>(new Number[] { 0, 0, 0, 0, 0 });
        numArr.forEach(n -> assertEquals(0, n));
        assertEquals(numArr.array, numArr.array());
        for (Number n : numArr)
            assertEquals(0, n);
        numArr.remove(2);
        assertEquals("[ 0, 0, null, 0, 0 ]", numArr.toString());
    }

    @ParameterizedTest
    @MethodSource("allArrays")
    public <N extends Number> void testCast(ArrayWrapper<N, Number, ?> array) {
        assertTrue(array.cast(arrSize) instanceof N);
    }

}
