package check;

import java.util.function.ToIntFunction;

/**
 * class implemeting ContestedCheck using two ToIntFunctions
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <A> the class of the first object in the contest
 * @param <B> the class of the second object in the contest
 */
public class Contest<A, B> implements ContestedCheck<A, B> {

	private ToIntFunction<? super A> aFunc;
	private ToIntFunction<? super B> bFunc;

	public Contest(ToIntFunction<? super A> aFunc,
			ToIntFunction<? super B> bFunc) {
		this.aFunc = aFunc;
		this.bFunc = bFunc;
	}

	@Override
	public ToIntFunction<? super A> aFunc() {
		return aFunc;
	}

	@Override
	public ToIntFunction<? super B> bFunc() {
		return bFunc;
	}

	@Override
	public int aVal(A a) {
		return aFunc.applyAsInt(a);
	}

	@Override
	public int bVal(B b) {
		return bFunc.applyAsInt(b);
	}

}
