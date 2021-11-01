package check;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import util.Math;

public abstract class IntCheckSet<E> implements IntCheck<E> {

    private IntCheck<E>[] intChecks;

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
        return count(intChecks, val, goal);
    }

    private boolean uncountable(E val) {
        return val == null || intChecks == null || intChecks.length == 0;
    }

    protected abstract int count(Stream<ToIntFunction<E>> stream, E val);

    protected abstract int count(IntCheck<E>[] checks, E val, int goal);

    public static class Sum<S> extends IntCheckSet<S> {

        @SafeVarargs
        public Sum(IntCheck<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(Stream<ToIntFunction<S>> stream, S val) {
            return Math.sum(stream, val);
        }

        @Override
        protected int count(IntCheck<S>[] checks, S val, int goal) {
            int rem = goal;
            for (IntCheck<S> check : checks) {
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
        protected int count(IntCheck<S>[] checks, S val, int goal) {
            return Math.min(Stream.of(checks), val, goal);
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
        protected int count(IntCheck<S>[] checks, S val, int goal) {
            int max = 0;
            for (IntCheck<S> check : checks) {
                int cur = check.quickInt(val, goal);
                if (cur >= goal)
                    return cur;
                if (cur >= max)
                    max = cur;
            }
            return max;
        }
    }
}
