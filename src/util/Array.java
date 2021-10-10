package util;

import java.util.Iterator;

/**
 * Shell class to make an array into an iterable. Also screen null elements
 * 
 * @author Liam
 * @date 7/22/2021
 * 
 * @param <E> type of object stored in array
 */
public class Array<E> implements IterableExt<E> {

	private E[] arr;

	@SafeVarargs
	public Array(E... array) {
		arr = array;
	}

	public int size() {
        return arr.length;
    }

	@Override
	public IndexIt<E> iterator() {
		return new It();
	}

	public IndexIt<E> iterator(int start, int max) {
		return new It(start, max);
	}

	public IterableExt<E> range(int start, int max) {
		return () -> iterator(start, max);
	}

	public Iterator<E> setIt(int... indexes) {
		return new SetIt(indexes);
	}

	public IterableExt<E> subSet(int... indexes) {
		return () -> setIt(indexes);
	}

	private class It implements IndexIt<E> {

		private int index;
		private int nulls;
		private int max;

		public It() {
			this(0, arr.length);
		}

		public It(int start, int max) {
			index = start;
			this.max = max;
			if (isNull()) // screens null as first element
				next();
		}

		@Override
		public int prevIndex() {
			return index - 1 - nulls;
		}

		@Override
		public boolean hasNext() {
			return index < max;
		}

		@Override
		public E next() {
			E ret = arr[index++];
			while (isNull()) { // screens null elements
				index++;
				nulls++;
			}
			return ret;
		}

		private boolean isNull() {
			return hasNext() && arr[index] == null;
		}

	}

	private class SetIt implements Iterator<E> {

		private int[] indexes;
		private int i;

		public SetIt(int... indexes) {
			this.indexes = indexes;
		}

		@Override
		public boolean hasNext() {
			return i < indexes.length && indexes[i] < arr.length;
		}

		@Override
		public E next() {
			return arr[i++];
		}

	}

    

}
