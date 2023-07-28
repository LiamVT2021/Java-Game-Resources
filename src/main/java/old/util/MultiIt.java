package old.util;

import java.util.Iterator;

/**
 * Allows iterating over multiple Iterables without having to merge them
 * 
 * @author Liam
 * @date 7/1/2021
 *
 * @param <E> type of Object in Iterable
 */
public class MultiIt<E> implements Iterable<E> {

	private Iterable<? extends Iterable<E>> col;

	// @SafeVarargs
	// public MultiIt(Iterable<E>... its){
	// 	this.col = new Array<Iterable<E>>(its);
	// }

	public MultiIt(Iterable<? extends Iterable<E>> col) {
		this.col = col;
	}

	@Override
	public Iterator<E> iterator() {
		return new It(col.iterator());
	}

	private class It implements Iterator<E> {

		private Iterator<? extends Iterable<E>> colIt;
		private Iterator<E> cur;

		private It(Iterator<? extends Iterable<E>> it) {
			colIt = it;
			do
				cur = nextIt();
			while (needNext());
		}

		@Override
		public boolean hasNext() {
			return cur != null;
		}

		@Override
		public E next() {
			E ret = cur.next();
			while (needNext())
				cur = nextIt();
			return ret;
		}

		private Iterator<E> nextIt() {
			if (colIt.hasNext())
				return colIt.next().iterator();
			return null;
		}

		private boolean needNext() {
			return cur != null && !cur.hasNext();
		}

	}

}
