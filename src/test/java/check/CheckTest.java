package check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckTest {

	private Check<String> strCheck;

	@BeforeEach
	public void setUp() {
		strCheck = str -> str.length() > 4;
	}

	@Test
	public void testTest() {
		assertTrue(strCheck.test("String"));
		assertFalse(strCheck.test("hi"));
	}

	@Test
	public void testStatic() {
		assertTrue(Check.True.test(null));
		assertFalse(Check.False.test(null));
		assertTrue(Check.NotNull.test("Hi"));
		assertFalse(Check.NotNull.test(null));
	}

	@Test
	public void testAnd() {
		Check<String> and = strCheck.and(Check.True);
		assertTrue(and.test("String"));
		assertFalse(and.test("hi"));
		BiCheck<String, Object> biAnd = strCheck.biAnd(Check.NotNull);
		assertTrue(biAnd.test("String", "hi"));
		assertFalse(biAnd.test("False", null));
	}

}
