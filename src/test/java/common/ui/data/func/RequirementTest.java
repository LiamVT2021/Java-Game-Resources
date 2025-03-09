package common.ui.data.func;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

/**
 * @version 3/8/25
 */
public class RequirementTest {
    private static Requirement<Boolean> simple, full;

    @BeforeAll
    private static void setUp() {
        Predicate<Boolean> pred = bool -> bool;
        simple = new Requirement<>("simple", pred);
        full = new Requirement<>("full", "help text", pred);
    }

    /**
     * tests line, hoverOver and toString
     */
    @Test
    public void testString() {
        assertEquals("simple", simple.line());
        assertEquals("full", full.line());
        assertEquals(null, simple.hoverOver());
        assertEquals("help text", full.hoverOver());
        assertEquals("simple", simple.toString());
        assertEquals("full\nhelp text", full.toString());
    }

    @Test
    public void testResults() {
        Result<?, ?, Boolean> simpleTrue = simple.results(true);
        Result<?, ?, Boolean> fullFalse = full.results(false);
        assertTrue(simpleTrue.output);
        assertFalse(fullFalse.output);
        assertEquals("help text", fullFalse.hoverOver());
        assertEquals("simple: true => true", simpleTrue.toString());
        assertEquals("full: false => false\nhelp text", fullFalse.toString());
    }

}
