package common.ui.data.func;

import java.util.function.Predicate;

import common.ui.data.adt.HoverOver;

/**
 * A Predicate visible to the user
 * 
 * @param I the input type of this Method
 * @version 3/8/25
 */
public final class Requirement<I> implements Method.Bool<I>, HoverOver {
    private final String line, help;
    private final Predicate<I> pred;

    /**
     * @param description one line description of this Predicate
     * @param predicate
     */
    public Requirement(String description, Predicate<I> predicate) {
        this(description, null, predicate);
    }

    /**
     * @param description one line description of this Predicate
     * @param helpText    additional information about this this Predicate
     * @param predicate
     */
    public Requirement(String description, String helpText, Predicate<I> predicate) {
        line = description;
        help = helpText;
        pred = predicate;
    }

    @Override
    public String line() {
        return line;
    }

    @Override
    public String hoverOver() {
        return help;
    }

    @Override
    public String toString() {
        return help == null ? line : line + '\n' + help;
    }

    @Override
    public boolean test(I input) {
        return pred.test(input);
    }

    @Override
    public Result<I, Requirement<I>, Boolean> results(I input) {
        return new ReqResult(input);
    }

    private final class ReqResult extends Result<I, Requirement<I>, Boolean> {
        private ReqResult(I input) {
            super(input);
        }

        @Override
        public Requirement<I> method() {
            return Requirement.this;
        }

        @Override
        public String hoverOver() {
            return help;
        }

        @Override
        public String toString() {
            return help == null ? line() : line() + '\n' + help;
        }
    }

}
