package old.body;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WoundTest {

	Wound wound;

	@BeforeAll
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
