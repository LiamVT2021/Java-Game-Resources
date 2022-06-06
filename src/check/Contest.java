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
public interface Contest<A, B> {

	public boolean contest(A a, B b);

	public static class Simple<C> implements Contest<C, C> {

		private final ToIntFunction<? super C> func;

		public Simple(ToIntFunction<? super C> func) {
			this.func = func;
		}

		@Override
		public boolean contest(C a, C b) {
			return func.applyAsInt(a) >= func.applyAsInt(b);
		}

	}

	public static class Int<A, B> implements Contest<A, B> {

		private final ToIntFunction<? super A> aFunc;
		private final ToIntFunction<? super B> bFunc;

		public Int(ToIntFunction<? super A> aFunc, ToIntFunction<? super B> bFunc) {
			this.aFunc = aFunc;
			this.bFunc = bFunc;
		}

		@Override
		public boolean contest(A a, B b) {
			return aFunc.applyAsInt(a) >= bFunc.applyAsInt(b);
		}
	}

}
