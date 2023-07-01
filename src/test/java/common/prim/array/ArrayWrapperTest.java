package common.prim.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests all methods for Primitive and Generic ArrayWrappers.
 * 
 * @version 6/30/23
 */
public class ArrayWrapperTest {

    private static final int arrSize = 5;

    private GenericArray<Number> numArr;
    private ByteArray byteArr;
    private ShortArray shortArr;
    private IntArray intArr;
    private LongArray longArr;
    private FloatArray floatArr;
    private DoubleArray doubleArr;

    @BeforeEach
    private void setUp() {
        numArr = new GenericArray<>(new Number[] { 0, 0, 0, 0, 0 });
        byteArr = new ByteArray(arrSize);
        shortArr = new ShortArray(arrSize);
        intArr = new IntArray(arrSize);
        longArr = new LongArray(arrSize);
        floatArr = new FloatArray(arrSize);
        doubleArr = new DoubleArray(arrSize);
    }

    private void forEach(Consumer<ArrayWrapper<? extends Number, Number, ?>> consumer) {
        Stream.of(numArr, byteArr, shortArr, intArr, longArr, floatArr, doubleArr).forEach(consumer);
    }

    @Test
    public void testCapacity() {
        forEach(arr -> assertEquals(arrSize, arr.capacity()));
    }

    @Test
    public void testGet() {
        forEach(arr -> {
            assertEquals(0, arr.get(0).intValue());
            assertThrows(IndexOutOfBoundsException.class, () -> arr.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> arr.get(arrSize));
        });
    }

    @Test
    public void testSwap() {
        Number n = 2;
        forEach(arr -> {
            assertEquals(0, arr.swap(3, n).intValue());
            assertEquals(n, arr.remove(3).intValue());
            assertThrows(IndexOutOfBoundsException.class, () -> arr.set(arrSize, n));
        });
    }

    @Test
    public void testToString() {
        String intString = "[ 0, 0, 0, 0, 0 ]";
        String floatString = "[ 0.0, 0.0, 0.0, 0.0, 0.0 ]";
        forEach(arr -> assertEquals(arr instanceof PrimArray && ((PrimArray<?, ?>) arr).storesFloat()
                ? floatString
                : intString, arr.toString()));
    }

    @Test
    public void testPrimStream() {
        intArr.intStream().forEach(i -> assertEquals(0, i));
        longArr.longStream().forEach(i -> assertEquals(0, i));
        doubleArr.doubleStream().forEach(i -> assertEquals(0, i));
    }

    @Test
    public void testGen() {
        numArr.forEach(n -> assertEquals(0, n));
        assertEquals(numArr.array, numArr.array());
        for (Number n : numArr)
            assertEquals(0, n);
        numArr.remove(2);
        assertEquals("[ 0, 0, null, 0, 0 ]", numArr.toString());
    }

    @Test
    public void testCast() {
        assertTrue(numArr.cast(arrSize) instanceof Number);
        assertTrue(byteArr.cast(arrSize) instanceof Byte);
        assertTrue(shortArr.cast(arrSize) instanceof Short);
        assertTrue(intArr.cast(arrSize) instanceof Integer);
        assertTrue(longArr.cast(arrSize) instanceof Long);
        assertTrue(floatArr.cast(arrSize) instanceof Float);
        assertTrue(doubleArr.cast(arrSize) instanceof Double);
    }

}
