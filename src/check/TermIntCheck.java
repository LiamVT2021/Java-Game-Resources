package check;

public interface TermIntCheck<C> extends IntCheck<C> {

	public int max();

	public PassFail termPF(C checkItem, int goal, int maxFail);

	public default int termInt(C checkItem, int goal) {
		return termPF(checkItem, goal, max() - goal).getPass();
	}

	@Override
	public default boolean test(C checkItem, Integer goal) {
		return termInt(checkItem, goal) >= goal;
//		if (goal != null && goal > max())
//			return false;
//		return IntCheck.super.test(checkItem, goal);
	}

	@Override
	public default Check<? super C> range(int min, int max) {
		return c -> {
			int i = termPF(c, max + 1, max() - min).getPass();
			return min <= i && i <= max;
		};
//		if (min > max())
//			return Check.False;
//		return IntCheck.super.range(min, max);
	}

}
