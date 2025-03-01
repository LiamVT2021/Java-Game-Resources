package common.ui.data.func;

import java.util.function.Predicate;

/**
 * A Predicate with a description
 * 
 * @version 3/1/25
 */
public class BoolReq<S> implements Requirement<S> {
    private final String desc;
    private final Predicate<S> pred;

    /**
     * @param description one line description of this requirement
     * @param predicate   returns if a subject meets this requirement
     */
    public BoolReq(String description, Predicate<S> predicate) {
        desc = description;
        pred = predicate;
    }

    @Override
    public final String lineDescription() {
        return desc;
    }

    @Override
    public final boolean test(S subject) {
        return pred.test(subject);
    }

    @Override
    public final Result<Boolean> results(S subject) {
        return new BoolResult(subject);
    }

    @Override
    public String toString() {
        return fullDescription();
    }

    @Override
    public final boolean equals(Object other) {
        return this == other || other instanceof BoolReq && pred.equals(((BoolReq<?>) other).pred);
    }

    @Override
    public final int hashCode() {
        return pred.hashCode();
    }

    /**
     * A Predicate with a long description
     */
    public static final class Full<S> extends BoolReq<S> {
        private final String full;

        /**
         * @param description     one line description of this requirement
         * @param fullDescription longer description of this requirement
         * @param predicate       returns if a subject meets this requirement
         */
        public Full(String description, String fullDescription, Predicate<S> predicate) {
            super(description, predicate);
            full = fullDescription;
        }

        public String fullDescription() {
            return full;
        }

        @Override
        public String toString() {
            return lineDescription() + ":\n" + fullDescription();
        }
    }

    private final class BoolResult extends Result<Boolean> {
        private final boolean result;

        public BoolResult(S subject) {
            result = test(subject);
        }

        @Override
        public String text() {
            return BoolReq.this.lineDescription();
        }

        @Override
        public Boolean result() {
            return result;
        }

        @Override
        public String lineDescription() {
            return text() + ": " + result;
        }

        @Override
        public String fullDescription() {
            String line = lineDescription();
            return BoolReq.this instanceof BoolReq.Full ? line + '\n' + BoolReq.this.fullDescription() : line;
        }
    }

}
