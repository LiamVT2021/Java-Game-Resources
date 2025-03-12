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

    public static abstract class Bool<I> extends Result<BoolMethod<I>, I, Boolean> {
        public Bool(I input, String calc, boolean output, Result<?, ?, ?>[] children) {
            super(input, calc, output, children);
        }
    }

    public static abstract class Int<I> extends Result<IntMethod<I>, I, Integer> {
        public Int(I input, String calc, int output, Result<?, ?, ?>[] children) {
            super(input, calc, output, children);
        }
    }

}
