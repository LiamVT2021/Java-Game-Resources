package check;

import java.util.function.ToIntFunction;

/**
 * uses the ToIntFunction interface to satisfy the BiPredicate
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <C> the class we will be converting to an int
 */
@FunctionalInterface
public interface IntCheck<C>
		extends BiCheck<C, Integer>, ToIntFunction<C> {

	@Override
	public default boolean test(C checkItem, Integer goal) {
		if (goal == null)
			goal = 0;
		return applyAsInt(checkItem) >= goal;
	}

	public default boolean contest(C first, C second) {
		return applyAsInt(first) >= applyAsInt(second);
	}

	public default SimpleContest<C> contest() {
		return c -> applyAsInt(c);
	}

	public default Check<C> range(int min, int max) {
		return c -> {
			int i = applyAsInt(c);
			return i >= min && i <= max;
		};
	}

	public default IntCheck<C> sum(ToIntFunction<? super C> other) {
		return c -> applyAsInt(c) + other.applyAsInt(c);
	}

	public default <D> BiCheck<C, D> sum(
			ToIntFunction<? super D> other, int goal) {
		return (c, d) -> applyAsInt(c) + other.applyAsInt(d) >= goal;
	}

}
