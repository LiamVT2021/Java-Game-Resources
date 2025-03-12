package common.ui.data.func;

import java.util.stream.Stream;

public class BoolMethodSet<I> {
    private final BoolMethod<I>[] methods;
    public final BoolMethod<I> any, all;
    public final IntMethod<I> count;

    public BoolMethodSet(BoolMethod<I>[] methods) {
        this.methods = methods;
        any = new Any();
        all = new All();
        count = new Count();
    }

    private Result.Bool<?>[] results(I input) {
        return Stream.of(methods).map(m -> m.result(input)).toArray(Result.Bool[]::new);
    }

    private final class Any implements BoolMethod<I> {
        @Override
        public boolean test(I input) {
            return Stream.of(methods).anyMatch(m -> m.test(input));
        }

        @Override
        public Result.Bool<I> result(I input) {
            return new AnyResult(input, results(input));
        }
    }

    private final class AnyResult extends Result.Bool<I> {
        public AnyResult(I input, Result.Bool<?>[] children) {
            super(input, null, Stream.of(children).anyMatch(r -> (boolean) r.output), children);
        }

        @Override
        public BoolMethod<I> method() {
            return any;
        }
    }

    private final class All implements BoolMethod<I> {
        @Override
        public boolean test(I input) {
            return Stream.of(methods).allMatch(m -> m.test(input));
        }

        @Override
        public Result.Bool<I> result(I input) {
            return new AllResult(input, results(input));
        }
    }

    private final class AllResult extends Result.Bool<I> {
        public AllResult(I input, Result.Bool<?>[] children) {
            super(input, null, Stream.of(children).allMatch(r -> (boolean) r.output), children);
        }

        @Override
        public BoolMethod<I> method() {
            return all;
        }
    }

    private final class Count implements IntMethod<I> {
        @Override
        public int applyAsInt(I input) {
            return (int) Stream.of(methods).filter(m -> m.test(input)).count();
        }

        @Override
        public Result<? extends Method<?, ?>, I, Integer> result(I input) {
            return new CountResult(input, results(input));
        }
    }

    private final class CountResult extends Result.Int<I> {
        public CountResult(I input, Result<?, ?, ?>[] children) {
            super(input, null, (int) Stream.of(children).filter(r -> (boolean) r.output).count(), children);
        }

        @Override
        public IntMethod<I> method() {
            return count;
        }
    }

}
