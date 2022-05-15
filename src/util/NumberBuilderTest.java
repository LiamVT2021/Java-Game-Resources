package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.NumberBuilder.ROMAN;

import org.junit.Test;

public class NumberBuilderTest {

    private final NumberBuilder coinBuilder = new NumberBuilder(new char[] { '5', '2', '1', 'q', 'd', 'n', 'p' },
            new int[] { 500, 200, 100, 25, 10, 5, 1 });

    @Test
    public void testCoins() {
        assertEquals("", coinBuilder.apply(0));
        assertEquals("ppp", coinBuilder.apply(3));
        assertEquals("np", coinBuilder.apply(6));
        assertEquals("dnp", coinBuilder.apply(16));
        assertEquals("ddp", coinBuilder.apply(21));
        assertEquals("qpp", coinBuilder.apply(27));
        assertEquals("qqqdnp", coinBuilder.apply(91));
        assertEquals("21np", coinBuilder.apply(306));
        assertEquals("521qdnp", coinBuilder.apply(841));
        assertEquals("-npp", coinBuilder.apply(-7));
    }

    @Test
    public void testRoman() {
        assertEquals("", ROMAN.apply(0));
        assertEquals("III", ROMAN.apply(3));
        assertEquals("IV", ROMAN.apply(4));
        assertEquals("VI", ROMAN.apply(6));
        assertEquals("IX", ROMAN.apply(9));
        assertEquals("XVI", ROMAN.apply(16));
        assertEquals("XXI", ROMAN.apply(21));
        assertEquals("XXVII", ROMAN.apply(27));
        assertEquals("XCI", ROMAN.apply(91));
        assertEquals("CCCVI", ROMAN.apply(306));
        assertEquals("-VII", ROMAN.apply(-7));
    }

}
