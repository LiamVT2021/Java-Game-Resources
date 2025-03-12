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

    private final class Any implements BoolMethod<I> {
        @Override
        public boolean test(I input) {
            return Stream.of(methods).anyMatch(m -> m.test(input));
        }
    }

    private final class All implements BoolMethod<I> {
        @Override
        public boolean test(I input) {
            return Stream.of(methods).allMatch(m -> m.test(input));
        }
    }

    private final class Count implements IntMethod<I> {
        @Override
        public int applyAsInt(I input) {
            return (int) Stream.of(methods).filter(m -> m.test(input)).count();
        }
    }

}
