package check;

import java.util.function.BiPredicate;

/**
 * uses a set of BiChecks to satisfy IntBiCheck
 * 
 * @author Liam
 * @version 7/22/21
 *
 * @param <A> the class of the first object we are checking
 * @param <B> the class of the second object we are checking
 */
public class BiCheckSet<A, B> implements MaxIntBiCheck<A, B> {

	private BiPredicate<A, B>[] checks;
	private CountType type;

	public BiCheckSet(BiPredicate<A, B>[] checkArr,
			CountType countType) {
		checks = checkArr;
		type = countType;
	}

	@Override
	public int max() {
		return checks.length;
	}

	@Override
	public int quickInt(A a, B b, Integer goal) {
		if (a == null && b == null)
			return 0;
		return type.count(new It(a, b), goal);
	}

	private class It extends CountIt<BiPredicate<A, B>,Boolean> {

		private A a;
		private B b;

		public It(A a, B b) {
			super(checks);
			this.a = a;
			this.b = b;
		}

		@Override
		public Boolean next() {
			return it.next().test(a, b);
		}
	}

}
