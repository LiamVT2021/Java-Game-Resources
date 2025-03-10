package common.ui.data.func;

import java.util.function.Function;

/**
 * single arg Method visible to the user
 * 
 * @param I the input type of this Method
 * @param O the output type of this Method
 * @version 3/8/25
 */
public interface Method<I, O> extends Function<I, O> {

    /**
     * @param input the Object being input
     * @return Displayable Results of this Method
     */
    Result<? extends Method<?, ?>, I, O> results(I input);

}
