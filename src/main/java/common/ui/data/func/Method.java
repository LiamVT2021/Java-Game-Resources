package common.ui.data.func;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import common.ui.data.adt.Line;

/**
 * Describable single arg Method with displayable Results
 * 
 * @param I the input type of this Method
 * @param O the output type of this Method
 * @version 3/8/25
 */
public interface Method<I, O> extends Line, Function<I, O> {

    /**
     * @param input the Object being input
     * @return Displayable Results of this Method
     */
    Result<I, ? extends Method<I, O>, O> results(I input);

    /**
     * Describable Predicate with displayable Results
     * 
     * @param I the input type of this Method
     */
    static interface Bool<I> extends Method<I, Boolean>, Predicate<I> {
        @Override
        default Boolean apply(I input) {
            return test(input);
        }
    }

    /**
     * Describable ToIntFunction with displayable Results
     * 
     * @param I the input type of this Method
     */
    static interface Int<I> extends Method<I, Integer>, ToIntFunction<I> {
        @Override
        default Integer apply(I input) {
            return applyAsInt(input);
        }
    }

}
