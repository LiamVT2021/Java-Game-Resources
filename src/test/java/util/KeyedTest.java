package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test nonConstructor methods in KeyedMap class and Keyed and Named
 * 
 * @author Liam
 * @version 7/20/21
 */
public class KeyedTest {

	private class Person implements Named {
		private String first;
		private String last;

		private Person(String firstName, String lastName) {
			first = firstName;
			last = lastName;
		}

		@Override
		public String name(boolean full) {
			if (full)
				return first + " " + last;
			else
				return first;
		}
	}

	private Person liam;
	private Person neson;
	private Person may;
	private KeyedMap<String, Person> map;

	@BeforeEach
	public void setUp() {
		liam = new Person("Liam", "Snyder");
		neson = new Person("Liam", "Neson");
		may = new Person("April", "May");
		map = new KeyedMap<String, Person>();
		map.put(liam);
	}

	@Test
	public void testPut() {
		map.put(neson);
		assertEquals(neson, map.get("Liam"));
	}

	@Test
	public void testPutAll() {
		map.putAll(new Array<Person>(new Person[] { neson, may }));
		assertEquals(neson, map.get("Liam"));
		assertEquals(may, map.get("April"));
	}

	@Test
	public void testPutAbsent() {
		map.putIfAbsent(neson);
		assertEquals(liam, map.get("Liam"));
	}

}
