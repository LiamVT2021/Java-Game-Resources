package old.check;

import java.util.function.ToIntFunction;

/**
 * Represents a contest between two objects
 * 
 * both are converted to ints higher int wins
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <A> the class of the first object in the contest
 * @param <B> the class of the second object in the contest
 */
public interface ContestedCheck<A, B> extends BiCheck<A, B> {

	public ToIntFunction<? super A> aFunc();

	public ToIntFunction<? super B> bFunc();

	public int aVal(A a);

	public int bVal(B b);

	@Override
	public default boolean test(A first, B second) {
		return aVal(first) >= bVal(second);
	}

}
