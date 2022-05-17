package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * tests all the methods in the IterableExt interface
 * 
 * (and SComp and Rcomp classes aswell)
 * 
 * @author Liam
 * @date 7/2/2021
 */
public class IterableExtTest {

	private IterableExt<Integer> arr;
	private IterableExt<Integer> small;
	private IterableExt<Integer> empty;
	private SComp<Integer> comp;

	@BeforeEach
	public void setUp() {
		arr = new Array<Integer>(
				new Integer[] { 1, 5, 10, -2, 3, 6, 2, 8, 12 });
		small = new Array<Integer>(new Integer[] { 1, 2 });
		empty = new Array<Integer>(new Integer[0]);
		comp = new SComp<Integer>();
	}

	// @Test
	// public void testGetMax() {
	// 	assertEquals(12, arr.getMax(comp));
	// 	assertArrayEquals(new Integer[] { 12, 10, 8, 6 },
	// 			arr.getMax(comp, new Integer[4]));
	// 	assertEquals(null, empty.getMax(comp));
	// 	assertArrayEquals(new Integer[] { null, null, null, null },
	// 			empty.getMax(comp, new Integer[4]));
	// 	assertArrayEquals(new Integer[] { 2, 1, null, null },
	// 			small.getMax(comp, new Integer[4]));
	// }

	// @Test
	// public void testGetMin() {
	// 	assertEquals(-2, arr.getMin(comp));
	// 	assertArrayEquals(new Integer[] { -2, 1, 2, 3 },
	// 			arr.getMin(comp, new Integer[4]));
	// 	assertArrayEquals(new Integer[] { 1, 2, null, null },
	// 			small.getMin(comp, new Integer[4]));
	// }

}
