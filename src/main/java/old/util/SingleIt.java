package old.util;

import java.util.Iterator;

/**
 * small shell class to return a single object with an iterator
 * 
 * without having to make it into an iterable
 * 
 * @author Liam
 * @date 7/2/2021
 *
 * @param <E> the type of object we will return
 */
public class SingleIt<E> implements Iterator<E> {

	private E entry;

	public SingleIt(E entry) {
		this.entry = entry;
	}

	@Override
	public boolean hasNext() {
		return entry != null;
	}

	@Override
	public E next() {
		E ret = entry;
		entry = null;
		return ret;
	}

}
