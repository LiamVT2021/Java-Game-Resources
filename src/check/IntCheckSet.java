package check;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import util.Math;

public abstract class IntCheckSet<E> implements IntCheck<E> {

    private ToIntFunction<E>[] intChecks;

    protected abstract int count(Stream<ToIntFunction<E>> stream, E val);

    @SafeVarargs
    public IntCheckSet(ToIntFunction<E>... intChecks) {
        this.intChecks = intChecks;
    }

    @Override
    public int applyAsInt(E val) {
        if (val == null || intChecks == null || intChecks.length == 0)
            return 0;
        return count(Stream.of(intChecks), val);
    }

    public static class Sum<S> extends IntCheckSet<S> {

        @SafeVarargs
        public Sum(ToIntFunction<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(Stream<ToIntFunction<S>> stream, S val) {
            return Math.sum(stream, val);
        }

    }

    public static class Min<S> extends IntCheckSet<S> {

        @SafeVarargs
        public Min(ToIntFunction<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(Stream<ToIntFunction<S>> stream, S val) {
            return Math.min(stream, val);
        }

    }

    public static class Max<S> extends IntCheckSet<S> {

        @SafeVarargs
        public Max(ToIntFunction<S>... intChecks) {
            super(intChecks);
        }

        @Override
        protected int count(Stream<ToIntFunction<S>> stream, S val) {
            return Math.max(stream, val);
        }

    }

}
