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
	private int size;

	@SafeVarargs
	public Array(E... array) {
		arr = array;
		update();
	}

	// Size methods

	public int length() {
		return arr.length;
	}

	public int size() {
		return size;
	}

	public int update() {
		size = count();
		return size;
	}

	// Access methods

	private boolean outBounds(int index) {
		return index < 0 || index >= arr.length;
	}

	public E get(int index) {
		if (outBounds(index))
			return null;
		return arr[index];
	}

	public E remove(int index) {
		return set(index, null);
	}

	public E set(int index, E val) {
		if (outBounds(index))
			return null;
		E ret = arr[index];
		if (ret == null) {
			if (val != null)
				size++;
		} else if (val == null)
			size--;
		arr[index] = val;
		return ret;
	}

	// Iterator methods

	@Override
	public IndexIt<E> iterator() {
		return new It();
	}

	public IndexIt<E> iterator(int start) {
		return new It(start);
	}

	public IndexIt<E> iterator(int start, int cut) {
		return new It(start, cut);
	}

	// SubSet methods

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

		private It() {
			this(0, arr.length);
		}

		private It(int start) {
			this(start, arr.length);
		}

		private It(int start, int cut) {
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
