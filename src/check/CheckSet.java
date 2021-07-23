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
public class CheckSet<C> implements IntCheck<C> {

	private Predicate<C>[] checks;
	private CountType type;

	public CheckSet(Predicate<C>[] checkArr, CountType countType) {
		checks = checkArr;
		type = countType;
	}

	@Override
	public int quickInt(C checkItem, Integer goal) {
		if (checkItem == null)
			return 0;
		return type.count(new It(checkItem), goal);
	}

	private class It extends CountIt<Predicate<C>> {

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
