package check;

import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class CheckSet<C> implements Check<C> {

	private final Check<? super C>[] arr;

	public CheckSet(Check<? super C>[] checks) {
		arr = checks;
	}

	private static final int MinParSize = 5;

	protected Stream<Check<? super C>> stream() {
		Stream<Check<? super C>> ret = Stream.of(arr);
		if (arr.length > MinParSize)
			ret = ret.parallel();
		return ret;
	}

	protected Predicate<Check<? super C>> eval(C t) {
		return c -> c.test(t);
	}

	public static class All<C> extends CheckSet<C> {

		public All(Check<? super C>[] checks) {
			super(checks);
		}

		@Override
		public boolean test(C t) {
			return stream().allMatch(this.eval(t));
		}

	}

	public static class Any<C> extends CheckSet<C> {

		public Any(Check<? super C>[] checks) {
			super(checks);
		}

		@Override
		public boolean test(C t) {
			return stream().anyMatch(this.eval(t));
		}

	}

	public static class None<C> extends CheckSet<C> {

		public None(Check<? super C>[] checks) {
			super(checks);
		}

		@Override
		public boolean test(C t) {
			return stream().noneMatch(this.eval(t));
		}

	}

}
