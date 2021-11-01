package check;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import util.Math;

public abstract class IntCheckSet<E> implements IntCheck<E> {

    protected IntCheck<E>[] intChecks;

    @SafeVarargs
    public IntCheckSet(IntCheck<E>... intChecks) {
        this.intChecks = intChecks;
    }

    @Override
    public int applyAsInt(E val) {
        if (uncountable(val))
            return 0;
        return count(Stream.of(intChecks), val);
    }

    @Override
    public int quickInt(E val, int goal) {
        if (uncountable(val) || goal <= 0)
            return 0;
        return count(val, goal);
    }

    protected boolean uncountable(E val) {
        return val == null || intChecks == null || intChecks.length == 0;
    }

    protected abstract int count(Stream<ToIntFunction<E>> stream, E val);

    protected abstract int count(E val, int goal);

    public static abstract class Sum<S> extends IntCheckSet<S> {

        @SafeVarargs
        public Sum(IntCheck<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(Stream<ToIntFunction<S>> stream, S val) {
            return Math.sum(stream, val);
        }
    }

    public static class ForceSum<S> extends Sum<S> {

        @SafeVarargs
        public ForceSum(IntCheck<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(S val, int goal) {
            return count(Stream.of(intChecks), val);
        }

    }

    public static class QucikSum<S> extends Sum<S> {

        @SafeVarargs
        public QucikSum(IntCheck<S>... intChecks) {
            super(intChecks);
        }

        @Override
        public boolean range(S checkItem, int min, int max) {
            int i = quickInt(checkItem, max + 1);
            return min <= i && max >= i;
        }

        @Override
        protected int count(S val, int goal) {
            int rem = goal;
            for (IntCheck<S> check : intChecks) {
                rem -= check.quickInt(val, rem);
                if (rem <= 0)
                    return goal - rem;
            }
            return goal - rem;
        }
    }

    public static class Min<S> extends IntCheckSet<S> {

        @SafeVarargs
        public Min(IntCheck<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(Stream<ToIntFunction<S>> stream, S val) {
            return Math.min(stream, val);
        }

        @Override
        protected int count(S val, int goal) {
            return Math.min(Stream.of(intChecks), val, goal);
        }

        @Override
        public boolean revTest(S val, int goal) {
            if (uncountable(val))
                return 0 < goal;
            for (IntCheck<S> check : intChecks) {
                int cur = check.quickInt(val, goal);
                if (cur < goal)
                    return true;
            }
            return false;
        }
    }

    public static class Max<S> extends IntCheckSet<S> {

        @SafeVarargs
        public Max(IntCheck<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(Stream<ToIntFunction<S>> stream, S val) {
            return Math.max(stream, val);
        }

        @Override
        protected int count(S val, int goal) {
            return Math.max(Stream.of(intChecks), val, goal);
        }

        @Override
        public boolean test(S val, int goal) {
            if (uncountable(val))
                return 0 >= goal;
            for (IntCheck<S> check : intChecks) {
                int cur = check.quickInt(val, goal);
                if (cur >= goal)
                    return true;
            }
            return false;
        }
    }
}
