package util;

import java.util.stream.Stream;

import check.IntCheck;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
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

	private static int defInt(Integer i, int def) {
		return i == null ? def : i;
	}

	//

	public static class Operation {

		private static final int parrSize = 32;

		private IntBinaryOperator func;
		private Integer identity;

		public Operation(IntBinaryOperator func, Integer identity) {
			this.func = func;
			this.identity = identity;
		}

		public Integer reduce(IntStream stream, boolean parrallel) {
			if (parrallel)
				stream = fullParallel(stream);
			return identity == null ? opInt(stream.reduce(func)) : stream.reduce(identity, func);
		}

		public Integer reduce(int... ints) {
			int l = ints.length;
			if (l < 2)
				return l == 1 ? ints[0] : identity;
			if (l >= parrSize)
				return reduce(IntStream.of(ints), true);
			int ret = identity == null ? ints[0] : identity;
			for (int i : ints)
				ret = func.applyAsInt(ret, i);
			return ret;
		}

		public IntBinaryOperator func() {
			return func;
		}

		public ToIntBiFunction<IntStream, Boolean> streamOp() {
			return this::reduce;
		}

		public ToIntFunction<int[]> arrayOp() {
			return this::reduce;
		}
	}

	public static final Operation Sum = new Operation((a, b) -> a + b, 0);
	public static final Operation Product = new Operation((a, b) -> a * b, 1);
	public static final Operation Min = new Operation((a, b) -> a <= b ? a : b, null);
	public static final Operation Max = new Operation((a, b) -> a >= b ? a : b, null);

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

	public static <E> int streamOp(Stream<E> stream, ToIntFunction<E> mapper, ToIntFunction<IntStream> counter) {
		return counter.applyAsInt(stream.mapToInt(mapper));
	}

	//

	public static <E> ToIntFunction<ToIntFunction<E>> intChecker(E val) {
		return intCheck -> intCheck.applyAsInt(val);
	}

	public static <E> ToIntFunction<IntCheck<E>> intChecker(E val, int goal) {
		return intCheck -> intCheck.quickInt(val, goal);
	}

	//

	public static ToIntFunction<IntStream> minCounter(int def) {
		return stream -> defInt(min(stream), def);
	}

	public static ToIntFunction<IntStream> maxCounter(int def) {
		return stream -> defInt(max(stream), def);
	}

}
