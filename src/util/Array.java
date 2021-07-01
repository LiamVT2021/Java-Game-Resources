package util;

import java.util.Iterator;

/**
 * Shell class to make an array into an iterable. Also screen null elements
 * 
 * @author Liam
 * @date 7/1/2021
 * 
 * @param <E> type of object stored in array
 */
public class Array<E> implements IterableExt<E> {

	private E[] arr;

	public Array(E[] array) {
		arr = array;
	}

	@Override
	public Iterator<E> iterator() {
		return new It();
	}

	private class It implements Iterator<E> {

		private int index;

		public It() {
			// screens null as first element
			if (isNull())
				next();
		}

		@Override
		public boolean hasNext() {
			return index < arr.length;
		}

		@Override
		public E next() {
			E ret = arr[index];
			do
				index++;
			while (isNull());
			// screens null elements
			return ret;
		}

		private boolean isNull() {
			return hasNext() && arr[index] == null;
		}

	}

}
