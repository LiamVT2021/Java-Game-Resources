package rand.dice;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ConstantTest {

    private static Stream<Rollable> constant(int value) {
        return Stream.of(new Constant(value), new Range(value, value));
    }

    private static Stream<Rollable> five() {
        return constant(5);
    }

    @ParameterizedTest
    @MethodSource("five")
    public void testRoll(Rollable five) {
        assertEquals(5, five.roll());
        assertEquals(15, five.sum(3));
    }

    @ParameterizedTest
    @MethodSource("five")
    public void testRange(Rollable five) {
        assertEquals(5, five.min());
        assertEquals(5, five.max());
        assertTrue(five.isConstant());
        assertEquals("5", five.range());
        assertEquals("5", five.diceStr());
    }

    @ParameterizedTest
    @MethodSource("five")
    public void testArray(Rollable five) {
        assertArrayEquals(new int[] { 5, 5, 5, 5 }, five.array(4));
        assertArrayEquals(new int[0], five.array(0));
        assertArrayEquals(new int[] { -5, -5, -5 }, five.array(-3));
    }

}
