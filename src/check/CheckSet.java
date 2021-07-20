package check;

import java.util.function.Predicate;

/**
 * uses a set of checks to satisfy IntCheck
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <C> the class of object we are checking
 */
public class CheckSet<C> implements IntCheck<C> {

	private Predicate<C>[] checks;

	public CheckSet(Predicate<C>[] checkArr) {
		checks = checkArr;
	}

	@Override
	public int applyAsInt(C checkItem) {
		int count = 0;
		for (Predicate<C> check : checks) {
			if (check.test(checkItem))
				count++;
		}
		return count;
	}

}
