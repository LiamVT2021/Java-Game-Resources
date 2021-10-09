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

	private class It implements IndexIt<E> {

		private int index;
		private int nulls;

		public It() {
			if (isNull()) // screens null as first element
				next();
		}

		@Override
		public int index() {
			return index - nulls;
		}

//		@Override
//		public int prevIndex() {
//			return index - 1 - nulls;
//		}

//		@Override
//		public int max() {
//			return arr.length;
//		}
//
//		@Override
//		public int remaining() {
//			return arr.length - index;
//		}

		@Override
		public boolean hasNext() {
			return index < arr.length;
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
