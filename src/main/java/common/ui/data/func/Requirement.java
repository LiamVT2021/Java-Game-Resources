package common.ui.data.func;

import java.util.function.Predicate;

/**
 * A Predicate with a description
 * 
 * @param I the input type of this Method
 * @version 3/1/25
 */
public final class Requirement<I> implements Method.Bool<I> {
    private final String line, full;
    private final Predicate<I> pred;

    /**
     * @param description one line description of this requirement
     * @param predicate
     */
    public Requirement(String description, Predicate<I> predicate) {
        this(description, null, predicate);
    }

    /**
     * @param line      one line description of this requirement
     * @param full      longer description of this requirement
     * @param predicate
     */
    public Requirement(String line, String full, Predicate<I> predicate) {
        this.line = line;
        this.full = full;
        pred = predicate;
    }

    @Override
    public String lineDescription() {
        return line;
    }

    public String fullDescription() {
        return full != null ? full : line;
    }

    @Override
    public String toString() {
        return full == null ? line : line + ":\n" + full;
    }

    @Override
    public boolean test(I ipnut) {
        return pred.test(ipnut);
    }

    @Override
    public Result<Boolean> results(I ipnut) {
        return new BoolResult(ipnut);
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof Requirement && pred.equals(((Requirement<?>) other).pred);
    }

    @Override
    public int hashCode() {
        return pred.hashCode();
    }

    private final class BoolResult extends Result<Boolean> {
        private final boolean result;

        public BoolResult(I ipnut) {
            result = test(ipnut);
        }

        @Override
        public String text() {
            return line;
        }

        @Override
        public Boolean result() {
            return result;
        }

        @Override
        public String lineDescription() {
            return line + ": " + result;
        }

        @Override
        public String fullDescription() {
            String line = lineDescription();
            return full == null ? line : line + '\n' + full;
        }
    }

}
