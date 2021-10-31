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
public interface IntCheck<C> extends ToIntFunction<C> {

	public default int quickInt(C checkItem, int goal) {
		return applyAsInt(checkItem);
	}

	public default boolean test(C checkItem, int goal) {
		return quickInt(checkItem, goal) >= 0;
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

	// public default IntCheck<C> sum(ToIntFunction<? super C> other) {
	// if (other == null)
	// return this;
	// return (c, g) -> {
	// int i = quickInt(c, g);
	// if (i >= g)
	// return i;
	// return i + cast(other).quickInt(c, g - i);
	// };
	// }

	// public default <D> IntBiCheck<C, D> biSum(
	// ToIntFunction<? super D> other) {
	// if (other == null)
	// return (c, d, g) -> quickInt(c, g);
	// return (c, d, g) -> {
	// int i = quickInt(c, g);
	// if (i >= g)
	// return i;
	// return i + cast(other).quickInt(d, g - i);
	// };
	// }

	// public default <D> IntBiCheck<D, C> revBiSum(
	// ToIntFunction<? super D> other) {
	// if (other == null)
	// return (d, c, g) -> quickInt(c, g);
	// return (d, c, g) -> {
	// int i = quickInt(c, g);
	// if (i >= g)
	// return i;
	// return i + cast(other).quickInt(d, g - i);
	// };
	// }

	public static final IntCheck<Object> Zero = o -> 0;

	// public static <D> IntCheck<? super D> cast(
	// ToIntFunction<? super D> intFunc) {
	// if (intFunc instanceof IntCheck<?>)
	// return (IntCheck<? super D>) intFunc;
	// if (intFunc == null)
	// return Zero;
	// return (d, g) -> intFunc.applyAsInt(d);
	// }

}
