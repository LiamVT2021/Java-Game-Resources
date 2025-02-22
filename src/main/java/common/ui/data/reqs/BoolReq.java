package common.ui.data.reqs;

import java.util.function.Predicate;

/**
 * A Predicate with a description
 * 
 * @version 12/20/25
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
    public final String description() {
        return desc;
    }

    @Override
    public final boolean test(S subject) {
        return pred.test(subject);
    }

    @Override
    public final Results results(S subject) {
        return new BoolResult(subject);
    }

    @Override
    public String toString() {
        return description();
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
            return description() + ":\n" + fullDescription();
        }
    }

    private final class BoolResult extends Results {
        private final boolean passed;

        public BoolResult(S subject) {
            passed = test(subject);
        }

        @Override
        public String description() {
            return BoolReq.this.description();
        }

        @Override
        public Boolean passed() {
            return passed;
        }

        @Override
        public String line() {
            return description() + ' ' + passStr();
        }
    }

}
