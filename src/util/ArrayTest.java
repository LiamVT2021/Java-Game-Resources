package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test all the methods in the Array class
 * 
 * @author Liam
 * @date 7/22/2021
 */
public class ArrayTest {

	private Array<Integer> arr;
	private Array<Integer> nullArr;

	@BeforeAll
	public void setUp() {
		arr = new Array<Integer>(new Integer[] { 1, 3, 5 });
		nullArr = new Array<Integer>(
				new Integer[] { null, null, null });
		// Array<String> stringArr = new Array<String>("hi","bye");
	}

	@Test
	public void testIterator() {
		int sum = 0;
		for (Integer i : arr)
			sum += i;
		assertEquals(9, sum);
		int count = 0;
		for (@SuppressWarnings("unused")
		Integer i : nullArr)
			count++;
		assertEquals(0, count);
		IndexIt<Integer> it = nullArr.iterator();
		assertFalse(it.hasNext());
		assertEquals(0, it.prevIndex());
	}

}
