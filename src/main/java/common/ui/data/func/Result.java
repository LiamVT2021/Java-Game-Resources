package common.ui.data.func;

/**
 * Displayable Result of a Method
 * 
 * @param M the type of the Method
 * @param I the input type of the Method
 * @param O the output type of the Method
 * @version 3/8/25
 */
public abstract class Result<M extends Method<?, ?>, I, O> {
    public final I input;
    public final String calc;
    public final O output;
    public final Result<?, ?, ?>[] children;

    public Result(I input, String calc, O output, Result<?, ?, ?>[] children) {
        this.input = input;
        this.calc = calc;
        this.output = output;
        this.children = children;
    }

    public abstract M method();

}
