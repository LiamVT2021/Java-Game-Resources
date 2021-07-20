package check;

import java.util.function.Predicate;

/**
 * extends the Predicate interface to allow converting to BiChecks
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <C> the class of object we are testing
 */
@FunctionalInterface
public interface Check<C> extends Predicate<C> {

	public default <D> BiCheck<C, D> biAnd(
			Predicate<? super D> other) {
		if (other == null)
			return biNull();
		return (c, d) -> test(c) && other.test(d);
	}

	public default <D> BiCheck<C, D> biOr(
			Predicate<? super D> other) {
		if (other == null)
			return biNull();
		return (c, d) -> test(c) || other.test(d);
	}

	public default <D> BiCheck<C, D> biNull() {
		return (c, d) -> test(c);
	}

	public default <D> BiCheck<D, C> revNull() {
		return (d, c) -> test(c);
	}

	// static methods

	public static final Check<Object> True = c -> true;
	public static final Check<Object> False = c -> false;
	public static final Check<Object> NotNull = c -> c != null;

	public static <D> Check<D> cast(Predicate<? super D> pred) {
		return d -> pred.test(d);
	}

	// Override default methods to ensure typing

	@Override
	public default Check<C> and(Predicate<? super C> other) {
		return c -> test(c) && other.test(c);
	}

	@Override
	public default Check<C> or(Predicate<? super C> other) {
		return c -> test(c) || other.test(c);
	}

	@Override
	public default Check<C> negate() {
		return c -> !test(c);
	}

}
