package common.ui.data.reqs;

import java.util.function.Predicate;

/**
 * Displayable Predicate
 * 
 * @version 12/21/25
 */
public interface Requirement<S> extends Predicate<S> {
    static final String PASS = "[Y]", FAIL = "[N]";

    /**
     * @return one line description of this requirement
     */
    String description();

    /**
     * @return longer description of this requirement
     */
    default String fullDescription() {
        return description();
    }

    /**
     * evaluates the requirement
     * 
     * @param subject the object being tested
     * @return whether the subject meets the requirement
     */
    @Override
    boolean test(S subject);

    /**
     * @param subject the object being tested
     * @return String indicating pass or fail
     */
    default String passed(S subject) {
        return test(subject) ? PASS : FAIL;
    }

    /**
     * @param subject the object being tested
     * @return results of test results
     */
    Results results(S subject);

}
