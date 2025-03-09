package common.ui.data.func;

import java.util.function.ToIntFunction;

/**
 * ToIntFunction visible to the user
 * 
 * @param I the input type of this ToIntFunction
 */
public interface IntMethod<I> extends Method<I, Integer>, ToIntFunction<I> {

    @Override
    default Integer apply(I input) {
        return applyAsInt(input);
    }

}
