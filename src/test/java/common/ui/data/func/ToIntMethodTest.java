package common.ui.data.func;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.ToIntFunction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ToIntMethodTest {
    private static ToIntMethod<Integer> simple, full;

    @BeforeAll
    private static void setUp() {
        ToIntFunction<Integer> func = i -> i;
        simple = new ToIntMethod<>("simple", func);
        full = new ToIntMethod<>("full", "full description", i -> i.toString(), func);
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
     * tests applyAsInt and apply
     */
    @Test
    public void testApply() {
        assertEquals(1, simple.applyAsInt(1));
        assertEquals(2, full.apply(2));
        assertEquals(3, simple.applyAsInt(3));
        assertEquals(4, full.apply(4));
    }

    @Test
    public void testResults() {
        Result<Integer> simpleOne = simple.results(1);
        Result<Integer> fullTwo = full.results(2);
        assertEquals(1, simpleOne.result());
        assertEquals(2, fullTwo.result());
        assertEquals("simple: 1", simpleOne.toString());
        assertEquals("full: 2 = 2\nfull description", fullTwo.toString());
        assertEquals("full", fullTwo.text());
        assertEquals("2", fullTwo.calc());
        assertNull(simpleOne.children());
    }

    /**
     * tests equals and hashCode
     */
    @Test
    public void testEquals() {
        assertEquals(simple, simple);
        assertEquals(simple, full);
        assertNotEquals(simple, null);
        assertNotEquals(simple, new ToIntMethod<Integer>("other", i -> -i));
        assertEquals(simple.hashCode(), full.hashCode());
    }

}
