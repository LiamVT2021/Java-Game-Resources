package common.prim.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests all methods in the common.prim.array package
 * 
 * @version 6/27/23
 */
public class PrimArrayTest {

    private static final int arrSize = 5;

    private ByteArray byteArr;
    private ShortArray shortArr;
    private IntArray intArr;
    private LongArray longArr;
    private FloatArray floatArr;
    private DoubleArray doubleArr;

    @BeforeEach
    public void setUp() {
        byteArr = new ByteArray(arrSize);
        shortArr = new ShortArray(arrSize);
        intArr = new IntArray(arrSize);
        longArr = new LongArray(arrSize);
        floatArr = new FloatArray(arrSize);
        doubleArr = new DoubleArray(arrSize);
    }

    private void forEach(Consumer<PrimArray<?>> consumer) {
        Stream.of(byteArr, shortArr, intArr, longArr, floatArr, doubleArr).forEach(consumer);
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
            assertEquals(n, arr.get(3).intValue());
            assertThrows(IndexOutOfBoundsException.class, () -> arr.set(arrSize, n));
        });
    }

    @Test
    public void testToString() {
        String intString = "[ 0, 0, 0, 0, 0 ]";
        String floatString = "[ 0.0, 0.0, 0.0, 0.0, 0.0 ]";
        assertEquals(intString, byteArr.toString());
        assertEquals(intString, shortArr.toString());
        assertEquals(intString, intArr.toString());
        assertEquals(intString, longArr.toString());
        assertEquals(floatString, floatArr.toString());
        assertEquals(floatString, doubleArr.toString());
    }

    @Test
    public void testPrimStream() {
        intArr.intStream().forEach(i -> assertEquals(0, i));
        longArr.longStream().forEach(i -> assertEquals(0, i));
        doubleArr.doubleStream().forEach(i -> assertEquals(0, i));
    }

}
