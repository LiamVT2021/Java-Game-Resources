package common.ui.data.func;

import java.util.function.Function;
import java.util.function.Predicate;

import common.ui.data.adt.Describable;

/**
 * Describable single arg Method with displayable Results
 * 
 * @param I the input type of this Method
 * @param O the output type of this Method
 * @version 12/23/25
 */
public interface Method<I, O> extends Describable, Function<I, O> {

    /**
     * @param input the Object being input
     * @return Displayable Results of this Method
     */
    Result<O> results(I input);

    /**
     * Describable Predicate with displayable Results
     * 
     * @param I the input type of this Method
     * @version 12/23/25
     */
    static interface Bool<I> extends Method<I, Boolean>, Predicate<I> {
        @Override
        default Boolean apply(I input) {
            return test(input);
        }
    }

}
