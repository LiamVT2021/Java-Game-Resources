package old.check;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * extends the BiPredicate interface to allow interacting with Checks
 * 
 * @author Liam
 * @version 7/22/21
 * 
 * @param <A> the class of the first object in the test method
 * @param <B> the class of the second object in the test method
 */
@FunctionalInterface
public interface BiCheck<A, B> extends BiPredicate<A, B> {

	public default BiCheck<A, B> andA(Predicate<? super A> other) {
		if (other == null)
			return this;
		return (a, b) -> test(a, b) && other.test(a);
	}

	public default BiCheck<A, B> andB(Predicate<? super B> other) {
		if (other == null)
			return this;
		return (a, b) -> test(a, b) && other.test(b);
	}

	public default BiCheck<A, B> orA(Predicate<? super A> other) {
		if (other == null)
			return this;
		return (a, b) -> test(a, b) || other.test(a);
	}

	public default BiCheck<A, B> orB(Predicate<? super B> other) {
		if (other == null)
			return this;
		return (a, b) -> test(a, b) || other.test(b);
	}

	public default Check<A> check(B help) {
		return checkItem -> test(checkItem, help);
	}

	public default Check<B> revCheck(A help) {
		return checkItem -> test(help, checkItem);
	}

	// static methods

	public static final BiCheck<Object, Object> True = (c, d) -> true;
	public static final BiCheck<Object, Object> False = (c,
			d) -> false;

	public static <C, D> BiCheck<? super C, ? super D> make(
			Predicate<? super C> cPred, Predicate<? super D> dPred,
			boolean and) {
		if (cPred == null) {
			if (dPred == null)
				return False;
			return (c, d) -> dPred.test(d);
		}
		if (dPred == null)
			return (c, d) -> cPred.test(c);
		if (and)
			return (c, d) -> cPred.test(c) && dPred.test(d);
		else
			return (c, d) -> cPred.test(c) || dPred.test(d);
	}

	public static <C, D> BiCheck<? super C, ? super D> cast(
			BiPredicate<? super C, ? super D> biPred) {
		if (biPred instanceof BiCheck<?, ?>)
			return (BiCheck<? super C, ? super D>) biPred;
		if (biPred == null)
			return False;
		return (c, d) -> biPred.test(c, d);
	}

	// Override default methods to ensure typing

	@Override
	public default BiCheck<A, B> and(
			BiPredicate<? super A, ? super B> other) {
		if (other == null)
			return this;
		return (a, b) -> test(a, b) && other.test(a, b);
	}

	@Override
	public default BiCheck<A, B> or(
			BiPredicate<? super A, ? super B> other) {
		if (other == null)
			return this;
		return (a, b) -> test(a, b) || other.test(a, b);
	}

	@Override
	public default BiCheck<A, B> negate() {
		return (a, b) -> !test(a, b);
	}

}
