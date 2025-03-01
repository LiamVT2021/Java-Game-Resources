package common.ui.data.func;

import java.util.function.Predicate;

/**
 * Describable Predicate with displayable Results
 * 
 * @param I the input type of this Method
 * @version 12/23/25
 */
public interface Requirement<I> extends Method<I, Boolean>, Predicate<I> {

    @Override
    default Boolean apply(I input) {
        return test(input);
    }

}
