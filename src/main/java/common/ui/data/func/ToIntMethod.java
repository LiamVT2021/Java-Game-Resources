package common.ui.data.func;

import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * wrapper to make a ToIntFunction visible to the user
 * 
 * @param I the input type of this Method
 * @version 3/8/25
 */
public final class ToIntMethod<I> extends MethodWrap<I, ToIntFunction<I>, Integer> implements IntMethod<I> {
    private final Function<I, String> calc;

    /**
     * @param description one line description of this ToIntFunction
     * @param function
     */
    public ToIntMethod(String description, ToIntFunction<I> function) {
        this(description, null, null, function);
    }

    /**
     * @param description one line description of this ToIntFunction
     * @param helpText    additional information about this this ToIntFunction
     * @param calc        function for displaying the calculation
     * @param function
     */
    public ToIntMethod(String description, String helpText, Function<I, String> calc, ToIntFunction<I> function) {
        super(description, helpText, function);
        this.calc = calc;
    }

    @Override
    public int applyAsInt(I input) {
        return func.applyAsInt(input);
    }

    @Override
    public Result<ToIntMethod<I>, I, Integer> result(I input) {
        return new WrapResult<>(input, calc == null ? null : calc.apply(input));
    }

}
