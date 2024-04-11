package common.prim.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests all methods for Primitive and Generic ArrayWrappers.
 * 
 * @version 4/11/24
 */
public class ArrayWrapperTest {
    private static final int arrSize = 5;

    private static class NumArray extends GenericArray<Number> implements PrimArray<Number, Number[]> {
        public NumArray() {
            super(new Number[] { 0, 0, 0, 0, 0 });
        }

        @Override
        public boolean storesFloat() {
            return false;
        }

        @Override
        public Number sum(Number a, Number b) {
            return a.intValue() + b.intValue();
        }

        @Override
        public Number product(Number a, Number b) {
            return a.intValue() * b.intValue();
        }
    }

    private static Stream<PrimArray<? extends Number, ?>> empty() {
        return Stream.of(new ByteArray(arrSize), new ShortArray(arrSize), new IntArray(arrSize), new LongArray(arrSize),
                new FloatArray(arrSize), new DoubleArray(arrSize), new NumArray());
    }

    private static Stream<PrimArray<? extends Number, ?>> full() {
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
    @MethodSource("empty")
    public void testSetAll(ArrayWrapper<? extends Number, Number, ?> array) {
        array.setAll(1);
        array.forEach(n -> assertEquals(1, n.intValue()));
        array.setAll(0, 0, 2, 4);
        for (int i = 0; i < array.capacity(); i++)
            assertEquals(i % 2, array.get(i).intValue());
        array.setAll(-1, IntStream.of(1, 3));
        for (int i = 0; i < array.capacity(); i++)
            assertEquals((i % 2) * -1, array.get(i).intValue());
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
