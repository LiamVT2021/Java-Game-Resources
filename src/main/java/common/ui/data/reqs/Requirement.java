package common.ui.data.reqs;

import java.util.function.Predicate;

/**
 * Displayable Predicate
 * 
 * @version 12/23/25
 */
public interface Requirement<S> extends Method<S>, Predicate<S> {

    /**
     * evaluates the requirement
     * 
     * @param subject the object being tested
     * @return whether the subject meets the requirement
     */
    @Override
    boolean test(S subject);

}
