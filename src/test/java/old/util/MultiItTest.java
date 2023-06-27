package old.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * tests all the methods in the MultiIt class
 * 
 * @author Liam
 * @date 7/1/2021
 */
public class MultiItTest {

	private MultiIt<Integer> multi;
	private MultiIt<Integer> empty;

	@BeforeEach
	public void setUp() {
		ArrayList<Iterable<Integer>> list = new ArrayList<Iterable<Integer>>();
		ArrayList<Iterable<Integer>> emptyList = new ArrayList<Iterable<Integer>>();
		list.add(new Array<Integer>(new Integer[] { 1, 2, 3 }));
		list.add(new Array<Integer>(new Integer[] { 4, 5, 6 }));
		list.add(new Array<Integer>(new Integer[] { 7, 8, 9 }));
		emptyList.add(new Array<Integer>(new Integer[0]));
		multi = new MultiIt<Integer>(list);
		empty = new MultiIt<Integer>(emptyList);
	}

	@Test
	public void testIterator() {
		Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Iterator<Integer> it = multi.iterator();
		for (int i = 0; i < 9; i++) {
			assertTrue(it.hasNext());
			assertEquals(arr[i], it.next());
		}
		assertFalse(it.hasNext());
		Iterator<Integer> emptyIt = empty.iterator();
		assertFalse(emptyIt.hasNext());
	}

}
