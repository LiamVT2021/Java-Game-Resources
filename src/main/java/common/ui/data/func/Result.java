package common.ui.data.func;

import common.ui.data.adt.Line;

/**
 * Displayable Result of a Method
 * 
 * @param I the input type of the Method
 * @param M the type of the Method
 * @param O the output type of the Method
 * @version 3/8/25
 */
public abstract class Result<I, M extends Method<I, O>, O> implements Line {
    public final I input;
    public final O output;

    public Result(I input) {
        this.input = input;
        output = method().apply(input);
    }

    public Result(I input, O output) {
        this.input = input;
        this.output = output;
    }

    public abstract M method();

    @Override
    public String line() {
        return method().line() + ": " + input + " => " + output;
    }

    @Override
    public String toString() {
        return line();
    }
}
