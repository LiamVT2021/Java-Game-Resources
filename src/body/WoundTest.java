package body;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

class WoundTest {

	Wound wound;

	@Before
	public void setUp() {
		wound = new Wound("Cut", 1, 4, 2);
	}

	@Test
	public void testGetBleed() {
		assertEquals(6, wound.getBleed());
	}

	@Test
	public void testGetDamage() {
		assertEquals(7, wound.getDamage());
	}

}
