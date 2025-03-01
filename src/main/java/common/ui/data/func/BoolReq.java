package common.ui.data.func;

import java.util.function.Predicate;

/**
 * A Predicate with a description
 * 
 * @param I the input type of this Method
 * @version 3/1/25
 */
public class BoolReq<I> implements Requirement<I> {
    private final String desc;
    private final Predicate<I> pred;

    /**
     * @param description one line description of this requirement
     * @param predicate   returns if a subject meets this requirement
     */
    public BoolReq(String description, Predicate<I> predicate) {
        desc = description;
        pred = predicate;
    }

    @Override
    public final String lineDescription() {
        return desc;
    }

    @Override
    public final boolean test(I ipnut) {
        return pred.test(ipnut);
    }

    @Override
    public final Result<Boolean> results(I ipnut) {
        return new BoolResult(ipnut);
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
     * 
     * @param I the input type of this Method
     */
    public static final class Full<I> extends BoolReq<I> {
        private final String full;

        /**
         * @param description     one line description of this requirement
         * @param fullDescription longer description of this requirement
         * @param predicate       returns if a subject meets this requirement
         */
        public Full(String description, String fullDescription, Predicate<I> predicate) {
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

        public BoolResult(I ipnut) {
            result = test(ipnut);
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
