package common.ui.data.reqs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;

/**
 * @version 2/21/25
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
        assertEquals("simple", simple.lineDescription());
        assertEquals("full", full.lineDescription());
        assertEquals("simple", simple.fullDescription());
        assertEquals("full description", full.fullDescription());
        assertEquals("simple", simple.toString());
        assertEquals("full:\nfull description", full.toString());
    }

    @Test
    public void testTest() {
        assertTrue(simple.test(true));
        assertTrue(full.test(true));
        assertFalse(simple.test(false));
        assertFalse(full.test(false));
    }

    @Test
    public void testResults() {
        assertEquals("simple [Y]", simple.results(true).toString());
        assertEquals("simple [N]", simple.results(false).toString());
        assertEquals("full [Y]", full.results(true).toString());
        assertEquals("full [N]", full.results(false).toString());
    }

    @Test
    public void testEquals() {
        assertEquals(simple, simple);
        assertEquals(simple, full);
        assertNotEquals(simple, null);
        assertNotEquals(simple, new BoolReq<Boolean>("other", bool -> !bool));
        assertEquals(simple.hashCode(), full.hashCode());
    }

}
