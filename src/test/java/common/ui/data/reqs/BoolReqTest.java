package common.ui.data.reqs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

/**
 * Tests all methods for BoolReq and Requirement files.
 * 
 * @version 2/20/25
 */
public class BoolReqTest {
    private static Requirement<Boolean> simple, full;

    @BeforeAll
    private static void setUp() {
        Predicate<Boolean> pred = bool -> bool;
        simple = new BoolReq<>("simple", pred);
        full = new BoolReq.Full<>("full", "full description", pred);
    }

    @Test
    public void testDescription() {
        assertEquals("simple", simple.description());
        assertEquals("simple", simple.fullDescription());
        assertEquals("full", full.description());
        assertEquals("full description", full.fullDescription());
    }

    @Test
    public void testTest() {
        assertTrue(simple.test(true));
        assertTrue(full.test(true));
        assertFalse(simple.test(false));
        assertFalse(full.test(false));
    }

    @Test
    public void testReport() {
        assertEquals("simple [Y]", simple.report(true));
        assertEquals("simple [N]", simple.report(false));
        assertEquals("full [Y]", full.report(true));
        assertEquals("full [N]", full.report(false));
    }

}
