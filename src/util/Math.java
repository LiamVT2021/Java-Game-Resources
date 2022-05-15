package util;

import java.util.stream.Stream;

import check.IntCheck;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

public class Math {

	public static final Random random = new Random();

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

	public static class Operation {

		private static final int parrSize = 32;

		private IntBinaryOperator func;
		private Integer identity;

		public Operation(IntBinaryOperator func, Integer identity) {
			this.func = func;
			this.identity = identity;
		}

		public IntBinaryOperator func() {
			return func;
		}

		public Integer reduce(IntStream stream) {
			return identity == null ? opInt(stream.reduce(func)) : stream.reduce(identity, func);
		}

		public Integer parrReduce(IntStream stream) {
			return reduce(fullParallel(stream));
		}

		public Integer reduce(int... ints) {
			int l = ints.length;
			if (l < 2)
				return l == 1 ? ints[0] : identity;
			if (l >= parrSize)
				return parrReduce(IntStream.of(ints));
			int ret = identity == null ? ints[0] : identity;
			for (int i : ints)
				ret = func.applyAsInt(ret, i);
			return ret;
		}

		public <E> int map(Stream<E> stream, ToIntFunction<E> mapper) {
			return reduce(stream.mapToInt(mapper));
		}

	}

	public static final Operation Sum = new Operation((a, b) -> a + b, 0);
	public static final Operation Product = new Operation((a, b) -> a * b, 1);
	public static final Operation Min = new Operation(Math::min, null);
	public static final Operation Max = new Operation(Math::max, null);

	//

	public static int min(int a, int b) {
		return a <= b ? a : b;
	}

	public static int max(int a, int b) {
		return a >= b ? a : b;
	}

	//

	public static <E> int count(Stream<E> stream, Predicate<E> check) {
		return Sum.parrReduce(stream.mapToInt(e -> check.test(e) ? 1 : 0));
	}

	public static <E> int count(Stream<Predicate<E>> stream, E e) {
		return Sum.parrReduce(stream.mapToInt(pred -> pred.test(e) ? 1 : 0));
	}

	//

	public static <E> ToIntFunction<ToIntFunction<E>> intChecker(E val) {
		return intCheck -> intCheck.applyAsInt(val);
	}

	public static <E> ToIntFunction<IntCheck<E>> intChecker(E val, int goal) {
		return intCheck -> intCheck.quickInt(val, goal);
	}

	public static String roman(int value) {
		return NumberBuilder.ROMAN.apply(value);
	}

}
