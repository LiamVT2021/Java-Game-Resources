package common.prim;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.prim.array.*;

public class NumStreamTest {

    private static final int arrSize = 4;

    private static Stream<PrimArray<? extends Number, ?>> empty() {
        return Stream.of(new ByteArray(arrSize), new ShortArray(arrSize), new IntArray(arrSize),
                new LongArray(arrSize), new FloatArray(arrSize), new DoubleArray(arrSize));
    }

    @ParameterizedTest
    @MethodSource("empty")
    public void testToArray(PrimArray<? extends Number, ?> array) {
        array.set(0, 3);
        array.set(2, 5);
        assertArrayEquals(new int[] { 3, 0, 5, 0 }, array.intArr());
        assertArrayEquals(new long[] { 3, 0, 5, 0 }, array.longArr());
        assertArrayEquals(new double[] { 3, 0, 5, 0 }, array.doubleArr());
    }

    public static void assertNumEquals(NumStream numStream, int... values) {
        assertArrayEquals(values, numStream.intArr());
    }

    public static void assertNumEquals(Stream<? extends Number> stream, int... values) {
        assertNumEquals(() -> stream, values);
    }

}
