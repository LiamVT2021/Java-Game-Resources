package util;

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

	public Array(E[] array) {
		arr = array;
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
			E ret = arr[index];
			index++;
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

}
