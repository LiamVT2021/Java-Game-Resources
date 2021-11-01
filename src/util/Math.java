package util;

import java.util.stream.Stream;

import check.IntCheck;

import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class Math {

	public static int divideUp(int num, int div) {
		return (num + div - 1) / div;
	}

	// public static int[] divideUp(int[] nums, int div) {
	// int mod = div-1;
	// int[] ret = new int[nums.length];
	// for (int num:nums){
	//
	// }
	// }

	private static IntStream fullParallel(IntStream ints) {
		return ints.parallel().unordered();
	}

	private static Integer opInt(OptionalInt i) {
		return i.isPresent() ? i.getAsInt() : null;
	}

	private static <E> IntStream toIntStream(Stream<ToIntFunction<E>> stream, E e) {
		return stream.mapToInt(intCheck -> intCheck.applyAsInt(e));
	}

	private static <E> IntStream toIntStream(Stream<IntCheck<E>> stream, E e, int goal) {
		return stream.mapToInt(intCheck -> intCheck.quickInt(e, goal));
	}

	//

	public static int add(int a, int b) {
		return a + b;
	}

	public static int sum(IntStream ints) {
		return fullParallel(ints).reduce(0, Math::add);
	}

	public static int sum(int... ints) {
		return sum(IntStream.of(ints));
	}

	//

	public static int multiply(int a, int b) {
		return a * b;
	}

	public static int product(IntStream ints) {
		return fullParallel(ints).reduce(1, Math::multiply);
	}

	public static int product(int... ints) {
		return product(IntStream.of(ints));
	}

	//

	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public static int max(IntStream ints) {
		return opInt(fullParallel(ints).reduce(Math::max));
	}

	public static int max(int... ints) {
		return max(IntStream.of(ints));
	}

	//

	public static int min(int a, int b) {
		return a < b ? a : b;
	}

	public static Integer min(IntStream ints) {
		return opInt(fullParallel(ints).reduce(Math::min));
	}

	public static Integer min(int... ints) {
		return min(IntStream.of(ints));
	}

	//

	public static <E> int count(Stream<E> stream, Predicate<E> check) {
		return sum(stream.mapToInt(e -> check.test(e) ? 1 : 0));
	}

	public static <E> int count(Stream<Predicate<E>> stream, E e) {
		return sum(stream.mapToInt(pred -> pred.test(e) ? 1 : 0));
	}

	//

	public static <E> int sum(Stream<E> stream, ToIntFunction<E> intCheck) {
		return sum(stream.mapToInt(intCheck));
	}

	public static <E> int sum(Stream<ToIntFunction<E>> stream, E e) {
		return sum(toIntStream(stream, e));
	}

	//

	public static <E> Integer max(Stream<E> stream, ToIntFunction<E> intCheck) {
		return max(stream.mapToInt(intCheck));
	}

	public static <E> Integer max(Stream<ToIntFunction<E>> stream, E e) {
		return max(toIntStream(stream, e));
	}

	// public static <E> Integer max(Stream<IntCheck<E>> stream, E e, int goal) {
	// 	return max(toIntStream(stream, e, goal));
	// }

	//

	public static <E> Integer min(Stream<E> stream, ToIntFunction<E> intCheck) {
		return min(stream.mapToInt(intCheck));
	}

	public static <E> Integer min(Stream<ToIntFunction<E>> stream, E e) {
		return min(toIntStream(stream, e));
	}

	public static <E> Integer min(Stream<IntCheck<E>> stream, E e, int goal) {
		return min(toIntStream(stream, e, goal));
	}

}
