package body;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WoundTest {

	Wound wound;

	@BeforeEach
	public void setUp() {
		wound = new Wound("Cut", 1, 4, 2, true);
	}

	@Test
	public void testGet() {
		assertEquals("Cut", wound.source());
		assertEquals(1, wound.nonLethal());
		assertEquals(4, wound.lethal());
		assertEquals(2, wound.maim());
		assertEquals(0, wound.getTreat());
	}

	@Test
	public void testGetBleed() {
		assertEquals(6, wound.getBleed());
	}

	@Test
	public void testGetDamage() {
		assertEquals(7, wound.getDamage());
	}

	@Test
	public void testLineString() {
		assertEquals("Cut: 1|4|2 *6 ^", wound.lineString(true));
		assertEquals("Cut: 7", wound.lineString(false));
		wound.treat(1);
		assertEquals("Cut: 1|4|2 ^ x100", wound.lineString(true));

	}

	@Test
	public void testCompareTo() {
		Wound other = new Wound("Bruise", 5, 3, 1, false);
		assertTrue(wound.compareTo(other) < 0);
		assertTrue(wound.compareTo(wound) == 0);
	}

}
