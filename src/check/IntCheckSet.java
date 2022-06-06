package check;

import java.util.function.IntPredicate;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

// import java.util.function.ToIntFunction;
// import java.util.stream.Stream;
// import util.Math;

public abstract class IntCheckSet<C> implements IntCheck<C> {

    // protected final IntCheck<C>[] arr;

    @Override
    public int applyAsInt(C t) {
        return sum(t, sum -> false);
    }

    public int sum(C t, IntPredicate breakFunc) {
        int sum = 0;
        for (IntCheck<C> check : arr) {
            sum += check.applyAsInt(t);
            if (breakFunc.test(sum))
                break;
        }
        return sum;
    }

}

// protected IntCheck<E>[] intChecks;

// @SafeVarargs
// public IntCheckSet(IntCheck<E>... intChecks) {
// this.intChecks = intChecks;
// }

// @Override
// public int applyAsInt(E val) {
// if (uncountable(val))
// return 0;
// return counter().map(Stream.of(intChecks), Math.intChecker(val));
// }

// @Override
// public int quickInt(E val, int goal) {
// if (uncountable(val))
// return 0;
// return counter().map(Stream.of(intChecks), quickMapper(val, goal));
// }

// protected ToIntFunction<IntCheck<E>> quickMapper(E val, int goal) {
// return Math.intChecker(val, goal);
// }

// protected boolean uncountable(E val) {
// return val == null || intChecks == null || intChecks.length == 0;
// }

// protected abstract Math.Operation counter();

// public static class ForceSum<S> extends IntCheckSet<S> {

// @SafeVarargs
// public ForceSum(IntCheck<S>... intChecks) {
// super(intChecks);
// }

// @Override
// protected ToIntFunction<IntCheck<S>> quickMapper(S val, int goal) {
// return intCheck -> intCheck.applyAsInt(val);
// }

// @Override
// protected Math.Operation counter() {
// return Math.Sum;
// }

// }

// public static class QucikSum<S> extends ForceSum<S> {

// @SafeVarargs
// public QucikSum(IntCheck<S>... intChecks) {
// super(intChecks);
// }

// @Override
// public int quickInt(S val, int goal) {
// if (uncountable(val))
// return 0;
// int rem = goal;
// for (IntCheck<S> check : intChecks) {
// rem -= check.quickInt(val, rem);
// if (rem <= 0)
// return goal - rem;
// }
// return goal - rem;
// }
// }

// public static class Min<S> extends IntCheckSet<S> {

// @SafeVarargs
// public Min(IntCheck<S>... intChecks) {
// super(intChecks);
// }

// @Override
// protected Math.Operation counter() {
// return Math.Min;
// }

// @Override
// public boolean revTest(S val, int goal) {
// if (uncountable(val))
// return 0 < goal;
// for (IntCheck<S> check : intChecks) {
// int cur = check.quickInt(val, goal);
// if (cur < goal)
// return true;
// }
// return false;
// }
// }

// public static class Max<S> extends IntCheckSet<S> {

// @SafeVarargs
// public Max(IntCheck<S>... intChecks) {
// super(intChecks);
// }

// @Override
// protected Math.Operation counter() {
// return Math.Max;
// }

// @Override
// public boolean test(S val, int goal) {
// if (uncountable(val))
// return 0 >= goal;
// for (IntCheck<S> check : intChecks) {
// int cur = check.quickInt(val, goal);
// if (cur >= goal)
// return true;
// }
// return false;
// }
// }
// }
