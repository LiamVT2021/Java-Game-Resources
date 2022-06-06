package check;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * extends the Predicate interface with CheckItem UI strings
 *
 * @author Liam
 * @version 5/17/22
 *
 * @param <C> the class of object we are testing
 */
@FunctionalInterface
public interface Check<C> extends Predicate<C>, IntCheck<C> {

	public default int value() {
		return 1;
	}

	@Override
	public default int applyAsInt(C t) {
		return test(t) ? value() : 0;
	}

	@Override
	default String message(C t) {
		String v = String.valueOf(value());
		return test(t) ? v : "(" + v + ")";
	}

	// sub classes

	public static class Desc<C> implements Check<C> {

		private final Predicate<C> pred;
		private final String desc;

		public Desc(Predicate<C> predicate, String description) {
			pred = predicate;
			desc = description;
		}

		@Override
		public boolean test(C t) {
			return pred.test(t);
		}

		@Override
		public String description() {
			return desc;
		}

	}

	public static class Prog<C> extends Desc<C> {

		private final Function<C, String> progFunc;

		public Prog(Predicate<C> predicate, String description, Function<C, String> progressFunction) {
			super(predicate, description);
			progFunc = progressFunction;
		}

		@Override
		public String progString(C t) {
			return progFunc.apply(t);
		}
	}

	// static classes

	public static final Check<Object> True = new Desc<>(o -> true, "Always True");
	public static final Check<Object> False = new Desc<>(o -> false, "Always False");
	public static final Check<Object> NotNull = new Desc<>(o -> o != null, "Not Null");

}
