package util;

import java.util.Iterator;

/**
 * simple Shell class to make any Iterable into IterableExt
 * 
 * @author Liam
 * @date 7/1/2021
 *
 * @param <E> type of Object in Iterable
 */
public class IterableShell<E> implements IterableExt<E> {

	private Iterable<E> iter;

	public IterableShell(Iterable<E> iterable) {
		iter = iterable;
	}

	@Override
	public Iterator<E> iterator() {
		return iter.iterator();
	}

}
