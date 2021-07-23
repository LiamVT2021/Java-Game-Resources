package check;

import util.IndexIt;

/**
 * Enums with assossiated counting methods
 * 
 * @author Liam
 * @version 7/22/21
 */
public enum CountType {

	ORDER, SUM, MAX;

	public int count(IndexIt<Boolean> it, Integer goal) {
		if (it == null || !it.hasNext())
			return 0;
		switch (this) {
		case ORDER:
			if (goal == null)
				return ordered(it);
			return ordered(it, goal);
		case SUM:
			if (goal == null)
				return sumCount(it);
			return sumCount(it, goal);
		case MAX:
			if (goal == null)
				return max(it);
			return max(it, goal);
		default:
			return 0;
		}
	}

	public static int ordered(IndexIt<Boolean> it) {
		while (it.hasNext() && it.next())
			;
		return it.prevIndex();
	}

	public static int ordered(IndexIt<Boolean> it, int goal) {
		int count = 0;
		while (count < goal && it.hasNext() && it.next())
			count++;
		return count;
	}

	public static int sumCount(IndexIt<Boolean> it) {
		int count = 0;
		while (it.hasNext()) {
			if (it.next())
				count++;
		}
		return count;
	}

	public static int sumCount(IndexIt<Boolean> it, int goal) {
		int count = 0;
		while (it.hasNext()) {
			if (it.next()) {
				count++;
				if (count >= goal)
					return count;
			}
		}
		return count;
	}

	public int max(IndexIt<Boolean> it) {
		int max = 0;
		while (it.hasNext()) {
			if (it.next())
				max = it.prevIndex();
		}
		return max;
	}

	public int max(IndexIt<Boolean> it, int goal) {
		int max = 0;
		while (it.hasNext()) {
			if (it.next()) {
				max = it.prevIndex();
				if (max >= goal)
					return max;
			}
		}
		return max;
	}

}
