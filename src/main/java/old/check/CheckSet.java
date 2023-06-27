package old.check;

import java.util.function.Predicate;
import java.util.stream.Stream;

import old.util.Math;

/**
 * uses a set of Checks to satisfy IntCheck
 * 
 * @author Liam
 * @version 7/22/21
 *
 * @param <C> the class of object we are checking
 */
public abstract class CheckSet<C> implements IntCheck<C> {

	private Predicate<C>[] checks;

	@SafeVarargs
	public CheckSet(Predicate<C>... checkArr) {
		checks = checkArr;
	}

	protected Predicate<C>[] checks() {
		return checks;
	}

	protected abstract int count(Predicate<C>[] checks, C val);

	@Override
	public int applyAsInt(C val) {
		if (val == null || checks == null || checks.length == 0)
			return 0;
		return count(checks, val);
	}

	public static class Sum<S> extends CheckSet<S> {

		@SafeVarargs
		public Sum(Predicate<S>... checkArr) {
			super(checkArr);
		}

		@Override
		protected int count(Predicate<S>[] checks, S val) {
			return Math.count(Stream.of(checks), val);
		}

		@Override
		public int quickInt(S val, int goal) {
			if (goal <= 0)
				return 0;
			int sum = 0;
			for (Predicate<S> pred : checks()) {
				if (pred.test(val)) {
					sum++;
					if (sum >= goal)
						return sum;
				}
			}
			return sum;
		}

	}

	public static class Order<S> extends CheckSet<S> {

		@SafeVarargs
		public Order(Predicate<S>... checkArr) {
			super(checkArr);
		}

		@Override
		protected int count(Predicate<S>[] checks, S val) {
			return count(checks, val, checks.length);
		}

		@Override
		public int quickInt(S val, int goal) {
			Predicate<S>[] checks = checks();
			return count(checks, val, Math.min(goal, checks.length));
		}

		private int count(Predicate<S>[] checks, S val, int goal) {
			int count;
			for (count = 0; count < goal; count++) {
				if (!checks[count].test(val))
					return count;
			}
			return count;
		}

	}

	public static class Max<S> extends CheckSet<S> {

		@SafeVarargs
		public Max(Predicate<S>... checkArr) {
			super(checkArr);
		}

		@Override
		protected int count(Predicate<S>[] checks, S val) {
			int max;
			for (max = checks.length - 1; max > 0; max--) {
				if (checks[max].test(val))
					return max + 1;
			}
			return max + 1;
		}

	}

	public static class Min<S> extends CheckSet<S> {

		@SafeVarargs
		public Min(Predicate<S>... checkArr) {
			super(checkArr);
		}

		@Override
		protected int count(Predicate<S>[] checks, S val) {
			int min;
			for (min = 0; min < checks.length; min++) {
				if (checks[min].test(val))
					return min + 1;
			}
			return min;
		}

	}
}
