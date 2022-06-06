package check;

import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * extends the ToIntFunction interface with CheckItem UI strings
 *
 * @author Liam
 * @version 5/17/22
 *
 * @param <C> the class of object we are testing
 */
@FunctionalInterface
public interface IntCheck<C> extends ToIntFunction<C>, checkItem<C> {

	@Override
	default String message(C t) {
		return String.valueOf(applyAsInt(t));
	}

	public static final IntCheck<Object> Zero = new Desc<>(o -> 0, "Zero");

	public static class Desc<C> implements IntCheck<C> {

		private final ToIntFunction<C> check;
		private final String desc;

		public Desc(ToIntFunction<C> check, String description) {
			this.check = check;
			desc = description;
		}

		@Override
		public int applyAsInt(C t) {
			return check.applyAsInt(t);
		}

		@Override
		public String description() {
			return desc;
		}

	}

	public static class Prog<C> extends Desc<C> {

		private final Function<C, String> progFunc;

		public Prog(ToIntFunction<C> check, String description, Function<C, String> progFunc) {
			super(check, description);
			this.progFunc = progFunc;
		}

		@Override
		public String progString(C t) {
			return progFunc.apply(t);
		}
	}

}

// public default boolean contest(C first, C second) {
// return applyAsInt(first) >= applyAsInt(second);
// }

// public default SimpleContest<C> contest() {
// return c -> applyAsInt(c);
// }
