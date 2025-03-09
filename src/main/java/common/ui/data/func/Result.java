package common.ui.data.func;

import common.ui.data.adt.HoverOver;
import common.ui.data.adt.Line;

/**
 * Displayable Result of a Method
 * 
 * @param I the input type of the Method
 * @param M the type of the Method
 * @param O the output type of the Method
 * @version 3/8/25
 */
public abstract class Result<I, M extends Method<?, ?>, O> implements Line, HoverOver {
    public final I input;
    public final String calc;
    public final O output;

    public Result(I input, String calc, O output) {
        this.input = input;
        this.calc = calc;
        this.output = output;
    }

    public abstract M method();

    @Override
    public String line() {
        return calc == null ? method().line() + ": " + input + " => " + output
                : method().line() + ": " + input + " => " + calc + " = " + output;
    }

}
