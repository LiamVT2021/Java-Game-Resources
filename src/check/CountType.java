package check;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import util.Math;

/**
 * Enums with assossiated counting methods
 * 
 * @author Liam
 * @version 7/22/21
 */
public enum CountType {

	ORDER, SUM, MAX, MIN;

	private boolean isUnCountable(Object[] arr, Object val) {
		return val == null || arr == null || arr.length == 0;
	}

	public <E> int count(Predicate<E>[] preds, E val) {
		if (isUnCountable(preds, val))
			return 0;
		if (this == SUM)
			return Math.count(Stream.of(preds), val);
		int count = 0;
		if (this == ORDER) {
			for (Predicate<E> pred : preds) {
				if (pred.test(val))
					count++;
				else
					return count;
			}
		} else if (this == MAX) {
			for (Predicate<E> pred : preds) {
				if (!pred.test(val))
					return count;
				count++;
			}
		} else if (this == MIN) {
			for (Predicate<E> pred : preds) {
				count++;
				if (pred.test(val))
					return count;
			}
		}
		return count;
	}

	public <E> int count(ToIntFunction<E>[] checks, E val) {
		if (isUnCountable(checks, val))
			return 0;
		switch (this) {
			case SUM:
				return Math.sum(Stream.of(checks), val);
			case MIN:
				return Math.min(Stream.of(checks), val);
			case MAX:
				return Math.max(Stream.of(checks), val);
			default:
				return 0;
		}
	}

	// public CountType(To)

	// public int count(IndexIt<Boolean> it, Integer goal) {
	// if (it == null || !it.hasNext())
	// return 0;
	// switch (this) {
	// case ORDER:
	// if (goal == null)
	// return ordered(it);
	// return ordered(it, goal);
	// case SUM:
	// if (goal == null)
	// return sumCount(it);
	// return sumCount(it, goal);
	// case MAX:
	// if (goal == null)
	// return max(it);
	// return max(it, goal);
	// default:
	// return 0;
	// }
	// }

	// public static int ordered(IndexIt<Boolean> it) {
	// while (it.hasNext() && it.next())
	// ;
	// return it.prevIndex();
	// }

	// public static int ordered(IndexIt<Boolean> it, int goal) {
	// int count = 0;
	// while (count < goal && it.hasNext() && it.next())
	// count++;
	// return count;
	// }

	// public static int sumCount(IndexIt<Boolean> it) {
	// int count = 0;
	// while (it.hasNext()) {
	// if (it.next())
	// count++;
	// }
	// return count;
	// }

	// public static int sumCount(IndexIt<Boolean> it, int goal) {
	// int count = 0;
	// while (it.hasNext()) {
	// if (it.next()) {
	// count++;
	// if (count >= goal)
	// return count;
	// }
	// }
	// return count;
	// }

	// public int max(IndexIt<Boolean> it) {
	// int max = 0;
	// while (it.hasNext()) {
	// if (it.next())
	// max = it.prevIndex();
	// }
	// return max;
	// }

	// public int max(IndexIt<Boolean> it, int goal) {
	// int max = 0;
	// while (it.hasNext()) {
	// if (it.next()) {
	// max = it.prevIndex();
	// if (max >= goal)
	// return max;
	// }
	// }
	// return max;
	// }

}
