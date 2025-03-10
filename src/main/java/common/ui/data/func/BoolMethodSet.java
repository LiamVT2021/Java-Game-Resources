package common.ui.data.func;

import java.util.stream.Stream;

public class BoolMethodSet<I> implements IntMethod<I> {
    private final BoolMethod<I>[] methods;

    public BoolMethodSet(BoolMethod<I>[] methods) {
        this.methods = methods;
    }

    @Override
    public int applyAsInt(I input) {
        return (int) Stream.of(methods).filter(m -> m.test(input)).count();
    }

    @Override
    public Result<? extends Method<?, ?>, I, Integer> results(I input) {
        return new BoolSetResult(input, Stream.of(methods).map(m -> m.results(input)).toArray(Result[]::new));
    }

    private final class BoolSetResult extends Result<BoolMethodSet<I>, I, Integer> {
        public BoolSetResult(I input, Result<?, ?, ?>[] children) {
            super(input, null, (int) Stream.of(children).filter(r -> (boolean) r.output).count(), children);
        }

        @Override
        public BoolMethodSet<I> method() {
            return BoolMethodSet.this;
        }
    }

}
