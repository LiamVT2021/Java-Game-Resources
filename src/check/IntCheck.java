package check;

import java.util.function.ToIntFunction;

/**
 * Converts ToIntFunction to Check by comparing against a goal Integer
 * 
 * @author Liam
 * @version 7/22/21
 *
 * @param <C> the class we will be converting to an int
 */
@FunctionalInterface
public interface IntCheck<C>
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

	public default Check<C> range(int min, int max) {
		return c -> {
			int i = quickInt(c, max + 1);
			return i >= min && i <= max;
		};
	}

	public default IntCheck<C> sum(ToIntFunction<? super C> other) {
		if (other == null)
			return this;
		return (c, g) -> {
			int i = quickInt(c, g);
			if (i >= g)
				return i;
			return i + cast(other).quickInt(c, g - i);
		};
	}

	public default <D> IntBiCheck<C, D> biSum(
			ToIntFunction<? super D> other) {
		if (other == null)
			return (c, d, g) -> quickInt(c, g);
		return (c, d, g) -> {
			int i = quickInt(c, g);
			if (i >= g)
				return i;
			return i + cast(other).quickInt(d, g - i);
		};
	}

	public default <D> IntBiCheck<D, C> revBiSum(
			ToIntFunction<? super D> other) {
		if (other == null)
			return (d, c, g) -> quickInt(c, g);
		return (d, c, g) -> {
			int i = quickInt(c, g);
			if (i >= g)
				return i;
			return i + cast(other).quickInt(d, g - i);
		};
	}

	public static final IntCheck<Object> Zero = (o, g) -> 0;

	public static <D> IntCheck<? super D> cast(
			ToIntFunction<? super D> intFunc) {
		if (intFunc instanceof IntCheck<?>)
			return (IntCheck<? super D>) intFunc;
		if (intFunc == null)
			return Zero;
		return (d, g) -> intFunc.applyAsInt(d);
	}

}
