package common.ui.data.func;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

/**
 * @version 3/1/25
 */
public class BoolReqTest {
    private static Requirement<Boolean> simple, full;

    @BeforeAll
    private static void setUp() {
        Predicate<Boolean> pred = bool -> bool;
        simple = new BoolReq<>("simple", pred);
        full = new BoolReq.Full<>("full", "full description", pred);
    }

    /**
     * tests lineDescription, fullDescription and toString
     */
    @Test
    public void testDescription() {
        assertEquals("simple", simple.lineDescription());
        assertEquals("full", full.lineDescription());
        assertEquals("simple", simple.fullDescription());
        assertEquals("full description", full.fullDescription());
        assertEquals("simple", simple.toString());
        assertEquals("full:\nfull description", full.toString());
    }

    /**
     * tests test and apply
     */
    @Test
    public void testTest() {
        assertTrue(simple.test(true));
        assertTrue(full.apply(true));
        assertFalse(simple.test(false));
        assertFalse(full.apply(false));
    }

    @Test
    public void testResults() {
        Result<Boolean> simpleTrue = simple.results(true);
        Result<Boolean> fullFalse = full.results(false);
        assertTrue(simpleTrue.result());
        assertFalse(fullFalse.result());
        assertEquals("simple: true", simpleTrue.toString());
        assertEquals("full: false\nfull description", fullFalse.toString());
        assertNull(simpleTrue.calc());
        assertNull(fullFalse.children());
    }

    /**
     * tests equals and hashCode
     */
    @Test
    public void testEquals() {
        assertEquals(simple, simple);
        assertEquals(simple, full);
        assertNotEquals(simple, null);
        assertNotEquals(simple, new BoolReq<Boolean>("other", bool -> !bool));
        assertEquals(simple.hashCode(), full.hashCode());
    }

}
