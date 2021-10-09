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
public interface IntCheck<C> extends FinalIntCheck<C> {

	public default IntCheck<C> sum(ToIntFunction<? super C> other) {
		if (other == null)
			return this;
		IntCheck<? super C> check = cast(other);
		return (c, g) -> {
			int i = quickInt(c, g);
			if (i >= g)
				return i;
			return i + check.quickInt(c, g - i);
		};
	}

	public default <D> IntBiCheck<C, D> biSum(
			ToIntFunction<? super D> other) {
		if (other == null)
			return (c, d, g) -> quickInt(c, g);
		IntCheck<? super D> check = cast(other);
		return (c, d, g) -> {
			int i = quickInt(c, g);
			if (i >= g)
				return i;
			return i + check.quickInt(d, g - i);
		};
	}

	public default <D> IntBiCheck<D, C> revBiSum(
			ToIntFunction<? super D> other) {
		if (other == null)
			return (d, c, g) -> quickInt(c, g);
		IntCheck<? super D> check = cast(other);
		return (d, c, g) -> {
			int i = quickInt(c, g);
			if (i >= g)
				return i;
			return i + check.quickInt(d, g - i);
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
