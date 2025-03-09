package common.ui.data.func;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/**
 * @version 3/8/25
 */
public class MethodWrapTest {
    private static MethodWrap<Boolean, ?, Boolean> simpleBool, fullBool;
    private static MethodWrap<Integer, ?, Integer> simpleInt, fullInt;
    private static MethodWrap<?, ?, ?>[] simpleArr, fullArr;

    @BeforeAll
    private static void setUp() {
        Predicate<Boolean> pred = bool -> bool;
        ToIntFunction<Integer> func = i -> i;
        simpleBool = new Requirement<>("simple", pred);
        fullBool = new Requirement<>("full", "help text", pred);
        simpleInt = new ToIntMethod<>("simple", func);
        fullInt = new ToIntMethod<>("full", "help text", i -> i.toString(), func);
        simpleArr = new MethodWrap[] { simpleBool, simpleInt };
        fullArr = new MethodWrap[] { fullBool, fullInt };
    }

    private static MethodWrap<?, ?, ?>[] simpleArr() {
        return simpleArr;
    }

    private static MethodWrap<?, ?, ?>[] fullArr() {
        return fullArr;
    }

    /**
     * tests line, hoverOver and toString for simple wrappers
     */
    @ParameterizedTest
    @MethodSource("simpleArr")
    public void testStringSimple(MethodWrap<?, ?, ?> simple) {
        assertEquals("simple", simple.line());
        assertEquals(null, simple.hoverOver());
        assertEquals("simple", simple.toString());
    }

    /**
     * tests line, hoverOver and toString for full wrappers
     */
    @ParameterizedTest
    @MethodSource("fullArr")
    public void testFullString(MethodWrap<?, ?, ?> full) {
        assertEquals("full", full.line());
        assertEquals("help text", full.hoverOver());
        assertEquals("full\nhelp text", full.toString());
    }

    /**
     * tests the output of results
     */
    @Test
    public void testResults() {
        assertTrue(fullBool.results(true).output);
        assertEquals(1, fullInt.results(1).output);
        assertEquals("help text", fullInt.results(1).hoverOver());
        assertEquals("simple: 1 => 1", simpleInt.results(1).toString());
        assertEquals("full: 2 => 2 = 2\nhelp text", fullInt.results(2).toString());
    }

}
