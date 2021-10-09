package util;

import java.util.Iterator;

/**
 * Shell class to screen null elements from an iterator
 * 
 * Probably slower than checking != null, can also just use stream predicate
 * 
 * @author Liam
 * @date 7/3/2021
 *
 * @param <E> the type of object we will iterate
 */
public class NullIt<E> implements Iterator<E> {

	private Iterator<E> it;
	private E temp;

	public NullIt(Iterator<E> iter) {
		it = iter;
		while (canNext())
			temp = it.next();
	}

	@Override
	public boolean hasNext() {
		return temp != null;
	}

	@Override
	public E next() {
		E ret = temp;
		if (it.hasNext()) {
			temp = it.next();
			while (canNext())
				temp = it.next();
		} else
			temp = null;
		return ret;
	}

	private boolean canNext() {
		return temp == null && it.hasNext();
	}

}
