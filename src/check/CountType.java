package check;

import java.util.Iterator;

import util.IndexIt;

/**
 * Enums with assossiated counting methods
 * 
 * @author Liam
 * @version 7/22/21
 */
public enum CountType {

	ORDER, SUM, MAX;

	public int count(Iterable<Boolean> it) {
		if (it == null)
			return 0;
		return count(it.iterator());
	}

	public int count(Iterator<Boolean> it) {
		if (it.hasNext() || !it.hasNext())
			return 0;
		switch (this) {
		case ORDER:
			if (it instanceof IndexIt)
				return ordered((IndexIt<Boolean>) it);
			return ordered(it, null);
		case SUM:
			return sumCount(it);
		case MAX:
			if (it instanceof IndexIt)
				return max((IndexIt<Boolean>) it);
			return maxCount(it);
		default:
			return 0;
		}
	}

	public int count(Iterator<Boolean> it, int goal) {
		if (it.hasNext() || !it.hasNext())
			return 0;
		switch (this) {
		case ORDER:
			return ordered(it, goal);
		case SUM:
			return sumCount(it, goal);
		case MAX:
			if (it instanceof IndexIt)
				return max((IndexIt<Boolean>) it, goal);
			return maxCount(it, goal);
		default:
			return 0;
		}
	}

	public PassFail countPF(CountIt<?, Boolean> it) {
		if (it == null || !it.hasNext())
			return new PassFail();
		switch (this) {
		case ORDER:
			return orderedPF(it);
		case SUM:
			return sumCountPF(it);
		case MAX:
			return maxPF(it);
		default:
			return new PassFail();
		}
	}

	public PassFail countPF(CountIt<?, Boolean> it, Integer goal) {
		if (goal == null)
			return countPF(it);
		if (it == null || !it.hasNext())
			return new PassFail();
		switch (this) {
		case ORDER:
			return orderedPF(it, goal);
		case SUM:
			return sumCountPF(it, goal);
		case MAX:
			return maxPF(it, goal);
		default:
			return new PassFail();
		}
	}

	public PassFail termCount(CountIt<?, Boolean> it, Integer goal,
			Integer maxFail) {
		if (maxFail == null)
			return countPF(it, goal);
		if (maxFail < 0)
			return new PassFail(0, it.remaining());
		if (this != SUM)
			return countPF(it, goal);
		if (goal == null)
			return termSum(it, maxFail);
		return termSum(it, goal, maxFail);
	}

	////////////////

	private static int ordered(IndexIt<Boolean> it) {
		while (it.hasNext() && it.next())
			;
		return it.index();
	}

	private static int ordered(Iterator<Boolean> it, Integer goal) {
		int count = 0;
		if (goal == null) {
			while (it.hasNext() && it.next())
				count++;
		} else {
			while (count < goal && it.hasNext() && it.next())
				count++;
		}
		return count;
	}

	private static PassFail orderedPF(CountIt<?, Boolean> it) {
		return new PassFail(ordered(it), it.remaining());
	}

	private static PassFail orderedPF(CountIt<?, Boolean> it,
			int goal) {
		return new PassFail(ordered(it, goal), it.remaining());
	}

	////////////////

	private static int sumCount(Iterator<Boolean> it) {
		int sum = 0;
		while (it.hasNext())
			if (it.next())
				sum++;
		return sum;
	}

	private static int sumCount(Iterator<Boolean> it, int goal) {
		int sum = 0;
		while (it.hasNext())
			if (it.next()) {
				sum++;
				if (sum >= goal)
					break;
			}
		return sum;
	}

	private static PassFail sumCountPF(Iterator<Boolean> it) {
		PassFail pf = new PassFail();
		while (it.hasNext())
			pf.test(it.next());
		return pf;
	}

	private static PassFail sumCountPF(CountIt<?, Boolean> it,
			int goal) {
		PassFail pf = new PassFail();
		while (it.hasNext())
			if (pf.test(it.next()) && pf.atGoal(goal))
				break;
		pf.add(0, it.remaining());
		return pf;
	}

	//////////

	private static PassFail termSum(CountIt<?, Boolean> it, int goal,
			int maxFail) {
		PassFail pf = new PassFail();
		while (it.hasNext()) {
			if (pf.test(it.next())) {
				if (pf.atGoal(goal))
					break;
			} else if (pf.hasFail(maxFail))
				break;
		}
		pf.add(0, it.remaining());
		return pf;
	}

	private static PassFail termSum(CountIt<?, Boolean> it,
			int maxFail) {
		PassFail pf = new PassFail();
		while (it.hasNext()) {
			if (!pf.test(it.next()))
				if (pf.hasFail(maxFail))
					break;
		}
		pf.add(0, it.remaining());
		return pf;
	}

	////////////////

	private static int maxCount(Iterator<Boolean> it) {
		int max = 0;
		int count = 0;
		while (it.hasNext()) {
			count++;
			if (it.next())
				max = count;
		}
		return max;
	}

	private static int maxCount(Iterator<Boolean> it, int goal) {
		int max = 0;
		int count = 0;
		while (it.hasNext()) {
			count++;
			if (it.next()) {
				if (count >= goal)
					return count;
				max = count;
			}
		}
		return max;
	}

	private static int max(IndexIt<Boolean> it) {
		int max = 0;
		while (it.hasNext()) {
			if (it.next())
				max = it.index();
		}
		return max;
	}

	private static int max(IndexIt<Boolean> it, int goal) {
		int max = 0;
		while (it.hasNext()) {
			if (it.next()) {
				max = it.index();
				if (max >= goal)
					break;
			}
		}
		return max;
	}

	private static PassFail maxPF(IndexIt<Boolean> it) {
		int max = max(it);
		return new PassFail(max, it.index() - max);
	}

	private static PassFail maxPF(CountIt<?, Boolean> it, int goal) {
		return new PassFail(max(it, goal), it.remaining());
	}

	////////////////

	public int intCount(Iterable<Integer> it) {
		if (it == null)
			return 0;
		return intCount(it.iterator());
	}

	public int intCount(Iterator<Integer> it) {
		if (it == null || !it.hasNext())
			return 0;
		switch (this) {
		case SUM:
			return intSum(it);
		case MAX:
			return intMax(it);
		default:
			return 0;
		}
	}

	public int intCount(Iterator<Integer> it, Integer goal) {
		if (goal == null)
			return intCount(it);
		if (it == null || !it.hasNext())
			return 0;
		switch (this) {
		case SUM:
			return intSum(it, goal);
		case MAX:
			return intMax(it, goal);
		default:
			return 0;
		}
	}

	private static int intMax(Iterator<Integer> it) {
		int max = 0;
		while (it.hasNext()) {
			int i = it.next();
			if (i > max)
				max = i;
		}
		return max;
	}

	private static int intMax(Iterator<Integer> it, int goal) {
		int max = 0;
		while (it.hasNext()) {
			int i = it.next();
			if (i > max) {
				if (i >= goal)
					return i;
				max = i;
			}
		}
		return max;
	}

	private static int intSum(Iterator<Integer> it) {
		int sum = 0;
		while (it.hasNext())
			sum += it.next();
		return sum;
	}

	private static int intSum(Iterator<Integer> it, int goal) {
		int sum = 0;
		if (it instanceof CountItTerm)
			while (sum < goal && it.hasNext())
				sum += ((CountItTerm<?>) it).count(goal);
		else
			while (sum < goal && it.hasNext())
				sum += it.next();
		return sum;
	}

	//////////////////

	public int termIntCount(CountItTerm<?> it, int goal,
			int maxFails) {
		if (it == null || !it.hasNext())
			return 0;
		switch (this) {
		case SUM:
			return sumTermCount(it, goal, maxFails);
		case MAX:
			return maxTermCount(it, goal);
		default:
			return 0;
		}
	}

	private static int maxTermCount(CountItTerm<?> it, int goal) {
		int max = 0;
		while (it.hasNext()) {
			int i = it.termCount(goal);
			if (i >= max) {
				if (i >= goal)
					return i;
				max = i;
			}
		}
		return max;
	}

//	private static int sumTermCount(CountItTerm<?> it, int maxFails) {
//		PassFail rem = new PassFail(0, -maxFails);
//		while (rem.getFail() < 0 && it.hasNext())
//			it.pf(rem);
//		rem.add(0, maxFails);
//		return rem.getPass();
//	}

	private static int sumTermCount(CountItTerm<?> it, int goal,
			int maxFails) {
		PassFail rem = new PassFail(-goal, -maxFails);
		while (rem.getPass() < 0 && rem.getFail() < 0 && it.hasNext())
			it.pf(rem);
		rem.add(goal, maxFails);
		return rem.getPass();
	}
}
