package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
 * @version 7/12/23
 */
public class ArrayWrapperTest {

    private static final int arrSize = 5;

    private static Stream<ArrayWrapper<? extends Number, Number, ?>> empty() {
        return Stream.of(new GenericArray<>(new Number[] { 0, 0, 0, 0, 0 }),
                new ByteArray(arrSize), new ShortArray(arrSize), new IntArray(arrSize), new LongArray(arrSize),
                new FloatArray(arrSize), new DoubleArray(arrSize));
    }

    private static Stream<ArrayWrapper<? extends Number, Number, ?>> full() {
        return empty().map(array -> {
            for (int i = 0; i < arrSize; i++)
                array.set(i, i);
            return array;
        });
    }

    @ParameterizedTest
    @MethodSource("empty")
    public void testCapacity(ArrayWrapper<? extends Number, Number, ?> array) {
        assertEquals(arrSize, array.capacity());
    }

    @ParameterizedTest
    @MethodSource("full")
    public void testGet(ArrayWrapper<? extends Number, Number, ?> array) {
        assertArrayEquals(new int[] { 0, 2, 4 }, array.get(0, 2, 4).mapToInt(Number::intValue).toArray());
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(arrSize));
    }

    @ParameterizedTest
    @MethodSource("full")
    public void testSwap(ArrayWrapper<? extends Number, Number, ?> array) {
        Number n = 2;
        assertEquals(3, array.swap(3, n).intValue());
        assertEquals(n, array.remove(3).intValue());
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(arrSize, n));
    }

    @ParameterizedTest
    @MethodSource("full")
    public void testToString(ArrayWrapper<? extends Number, Number, ?> array) {
        assertEquals(array.getClass().getSimpleName() + ": "
                + (array instanceof PrimArray && ((PrimArray<?, ?>) array).storesFloat()
                        ? "[ 0.0, 1.0, 2.0, 3.0, 4.0 ]"
                        : "[ 0, 1, 2, 3, 4 ]"),
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
        assertEquals("[ 0, 0, null, 0, 0 ]", numArr.arrayString());
    }

    @ParameterizedTest
    @MethodSource("empty")
    public <N extends Number> void testCast(ArrayWrapper<N, Number, ?> array) {
        assertTrue(array.cast(arrSize) instanceof N);
    }

}
