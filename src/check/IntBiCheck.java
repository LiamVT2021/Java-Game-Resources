// package check;

// import java.util.function.ToIntBiFunction;
// import java.util.function.ToIntFunction;

// /**
//  * Converts ToIntBiFunction to BiCheck by comparing against a goal Integer
//  * 
//  * @author Liam
//  * @version 7/22/21
//  *
//  * @param <A> the class of the first object
//  * @param <B> the class of the second object
//  */
// @FunctionalInterface
// public interface IntBiCheck<A, B> extends ToIntBiFunction<A, B> {

// 	public int quickInt(A a, B b, Integer goal);

// 	@Override
// 	public default int applyAsInt(A a, B b) {
// 		return quickInt(a, b, null);
// 	}

// 	public default BiCheck<A, B> biCheck(Integer goal) {
// 		if (goal == null)
// 			return (a, b) -> quickInt(a, b, 1) > 0;
// 		return (a, b) -> quickInt(a, b, goal) >= goal;
// 	}

// 	public default BiCheck<A, B> range(int min, int max) {
// 		return (a, b) -> {
// 			int i = quickInt(a, b, max + 1);
// 			return i >= min && i <= max;
// 		};
// 	}

// 	public default IntBiCheck<A, B> sum(
// 			ToIntBiFunction<? super A, ? super B> other) {
// 		if (other == null)
// 			return this;
// 		return (a, b, g) -> {
// 			int i = quickInt(a, b, g);
// 			if (i >= g)
// 				return i;
// 			return i + cast(other).quickInt(a, b, g - i);
// 		};
// 	}

// 	public default IntCheck<A> check(B b) {
// 		return (a, g) -> quickInt(a, b, g);
// 	}

// 	public default IntCheck<B> revCheck(A a) {
// 		return (b, g) -> quickInt(a, b, g);
// 	}

// 	public static final IntBiCheck<Object, Object> Zero = (a, b,
// 			g) -> 0;

// 	public static <C, D> IntBiCheck<? super C, ? super D> cast(
// 			ToIntBiFunction<? super C, ? super D> intFunc) {
// 		if (intFunc instanceof IntBiCheck<?, ?>)
// 			return (IntBiCheck<? super C, ? super D>) intFunc;
// 		if (intFunc == null)
// 			return Zero;
// 		return (c, d, g) -> intFunc.applyAsInt(c, d);
// 	}

// 	public static <C, D> IntBiCheck<? super C, ? super D> make(
// 			ToIntFunction<? super C> aFunc,
// 			ToIntFunction<? super D> bFunc) {
// 		if (aFunc != null)
// 			return IntCheck.<C>cast(aFunc).<D>biSum(bFunc);
// 		if (bFunc != null)
// 			return IntCheck.<D>cast(bFunc).<C>revBiSum(aFunc);
// 		return Zero;
// 	}

// }
