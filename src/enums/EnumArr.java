package enums;

public class EnumArr<E extends Enum<?>> {

	private int[] arr;

//	public EnumArr(int length) {
//		arr = new int[length];
//	}

	private int index(E e) {
		if (e == null)
			return -1;
		return e.ordinal();
	}

	public int get(E e) {
		return arr[index(e)];
	}

	public void set(E e, int i) {
		arr[index(e)] = i;
	}

	public int mod(E e, int i) {
		int index = index(e);
		arr[index] += i;
		return arr[index];
	}

}
