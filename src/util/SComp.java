package util;

import java.util.Comparator;

/**
 * Simple class to ensure compatiblity of Comparables before runtime
 * 
 * @author Liam
 * @date 7/1/2021
 *
 * @param <E> type of Object we are comparing
 */
public class SComp<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		return o1.compareTo(o2);
	}

}
