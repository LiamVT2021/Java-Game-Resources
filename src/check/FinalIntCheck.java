package check;

import java.util.function.ToIntFunction;

public interface FinalIntCheck<C>
		extends BiCheck<C, Integer>, ToIntFunction<C> {

	public int quickInt(C checkItem, Integer goal);

	@Override
	public default int applyAsInt(C checkItem) {
		return quickInt(checkItem, null);
	}

	@Override
	public default boolean test(C checkItem, Integer goal) {
		if (goal == null)
			return quickInt(checkItem, 1) > 0;
		return quickInt(checkItem, goal) >= goal;
	}

	public default boolean contest(C first, C second) {
		return applyAsInt(first) >= applyAsInt(second);
	}

	public default SimpleContest<C> contest() {
		return c -> applyAsInt(c);
	}

	public default Check<? super C> range(int min, int max) {
		return c -> {
			int i = quickInt(c, max + 1);
			return i >= min && i <= max;
		};
	}

}
