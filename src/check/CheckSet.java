package check;

import java.util.function.Predicate;

/**
 * uses a set of Checks to satisfy IntCheck
 * 
 * @author Liam
 * @version 7/22/21
 *
 * @param <C> the class of object we are checking
 */
public class CheckSet<C> implements TermIntCheck<C> {

	private Predicate<? super C>[] checks;
	private CountType type;

	public CheckSet(Predicate<? super C>[] checkArr,
			CountType countType) {
		checks = checkArr;
		type = countType;
	}

	@Override
	public int max() {
		return checks.length;
	}

	@Override
	public PassFail termPF(C checkItem, int goal, int maxFail) {
		if (checkItem == null)
			return new PassFail();
		return type.termCount(new It(checkItem), goal, maxFail);
	}

	@Override
	public int quickInt(C checkItem, Integer goal) {
		if (checkItem == null)
			return 0;
		return type.count(new It(checkItem), goal);
	}

	private class It extends CountIt<Predicate<? super C>, Boolean> {

		private C item;

		public It(C checkItem) {
			super(checks);
			item = checkItem;
		}

		@Override
		public Boolean next() {
			return it.next().test(item);
		}

	}

}
