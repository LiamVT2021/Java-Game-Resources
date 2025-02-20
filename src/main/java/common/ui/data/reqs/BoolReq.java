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
    public String description() {
        return desc;
    }

    @Override
    public boolean test(S subject) {
        return pred.test(subject);
    }

    @Override
    public String results(S subject) {
        return description() + ' ' + passed(subject);
    }

    /**
     * A Predicate with a long description
     */
    public static class Full<S> extends BoolReq<S> {
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
    }

}
