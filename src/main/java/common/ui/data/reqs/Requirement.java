package common.ui.data.reqs;

import java.util.function.Predicate;

/**
 * Displayable Predicate
 * 
 * @version 12/20/25
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
     * @return one line description of test results
     */
    String results(S subject);

    /**
     * @param subject the object being tested
     * @return full of test results
     */
    default String report(S subject){
        return results(subject);
    }

}
