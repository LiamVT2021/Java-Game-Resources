package check;

public interface MaxIntBiCheck<A, B> extends IntBiCheck<A, B> {

	public int max();

	@Override
	public default boolean test(A a, B b, int goal) {
		if (goal > max())
			return false;
		return IntBiCheck.super.test(a, b, goal);
	}

	@Override
	public default BiCheck<? super A, ? super B> range(int min,
			int max) {
		if (min > max())
			return BiCheck.False;
		return IntBiCheck.super.range(min, max);
	}

}
