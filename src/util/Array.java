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

	public IndexIt<E> iterator(int start, int cut) {
		return new It(start, cut);
	}

	public IterableExt<E> range(int start, int cut) {
		return new RangeArray<>(this, start, cut);
	}

	public IterableExt<E> range(Enum<?> first, Enum<?> cut) {
		return range(first.ordinal(), cut != null ? cut.ordinal() : arr.length);
	}

	public IterableExt<E> subSet(int... indexes) {
		return new SubArray<E>(arr, indexes);
	}

	private class It implements IndexIt<E> {

		private int index;
		private int nulls;
		private int cut;

		public It() {
			this(0, arr.length);
		}

		public It(int start, int cut) {
			index = start;
			this.cut = cut;
			if (isNull()) // screens null as first element
				next();
		}

		@Override
		public int prevIndex() {
			return index - 1 - nulls;
		}

		@Override
		public boolean hasNext() {
			return index < cut;
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

}
