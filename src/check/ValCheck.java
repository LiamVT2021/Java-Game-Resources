package check;

import java.util.function.Function;
import java.util.function.Predicate;

public class ValCheck<C> implements Check<C> {

    private final Predicate<? super C> pred;
    private final int val;

    public ValCheck(Predicate<? super C> predicate, int value) {
        pred = predicate;
        val = value;
    }

    @Override
    public boolean test(C t) {
        return pred.test(t);
    }

    @Override
    public int value() {
        return val;
    }

    public static class Desc<C> extends ValCheck<C> {

        private final String desc;

        public Desc(Predicate<C> predicate, int value, String description) {
            super(predicate, value);
            desc = description;
        }

        @Override
        public String description() {
            return desc;
        }

    }

    public static class Prog<C> extends Desc<C> {

        private final Function<C, String> progFunc;

        public Prog(Predicate<C> predicate, int value, String description, Function<C, String> progressFunction) {
            super(predicate, value, description);
            progFunc = progressFunction;
        }

        @Override
        public String progString(C t) {
            return progFunc.apply(t);
        }
    }
}
