package common.prim.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    private Stream<PrimArray<?>> stream() {
        return Stream.of(byteArr, shortArr, intArr, longArr, floatArr, doubleArr);
    }

    @Test
    public void testCapacity() {
        stream().forEach(arr -> assertEquals(arrSize, arr.capacity()));
    }

    @Test
    public void testGet() {
        stream().forEach(arr -> {
            assertEquals(0, arr.get(0).intValue());
            assertThrows(IndexOutOfBoundsException.class, () -> arr.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> arr.get(arrSize));
        });
    }

    @Test
    public void testSwap() {
        Number n = 2;
        stream().forEach(arr -> {
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
