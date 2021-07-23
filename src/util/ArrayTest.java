package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test all the methods in the Array class
 * 
 * @author Liam
 * @date 7/22/2021
 */
public class ArrayTest {

	private Array<Integer> arr;
	private Array<Integer> nullArr;

	@BeforeEach
	public void setUp() {
		arr = new Array<Integer>(new Integer[] { 1, 3, 5 });
		nullArr = new Array<Integer>(
				new Integer[] { null, null, null });
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
