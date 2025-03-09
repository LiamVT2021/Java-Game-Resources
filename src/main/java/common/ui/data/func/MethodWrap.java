package common.ui.data.func;

import common.ui.data.adt.HoverOver;
import common.ui.data.adt.Line;

/**
 * wrapper to make a single arg function visible to the user
 * 
 * @param I the input type of this Method
 * @param F the type of the wrapped function
 * @param O the output type of this Method
 * @version 3/8/25
 */
public abstract class MethodWrap<I, F, O> implements Method<I, O> {
    protected final String line, help;
    protected final F func;

    /**
     * @param description one line description of this Method
     * @param helpText    additional information about this this Method
     * @param function
     */
    protected MethodWrap(String description, String helpText, F function) {
        line = description;
        help = helpText;
        func = function;
    }

    @Override
    public String line() {
        return line;
    }

    @Override
    public String hoverOver() {
        return help;
    }

    @Override
    public String toString() {
        return help == null ? line : line + '\n' + help;
    }

    protected class WrapResult<M extends Method<?, ?>> extends Result<M, I, O> implements Line, HoverOver {
        public WrapResult(I input, String calc) {
            super(input, calc, apply(input), null);
        }

        @Override
        @SuppressWarnings("unchecked")
        public M method() {
            return (M) MethodWrap.this;
        }

        @Override
        public String hoverOver() {
            return help;
        }

        @Override
        public String line() {
            return calc == null ? method().line() + ": " + input + " => " + output
                    : method().line() + ": " + input + " => " + calc + " = " + output;
        }

        @Override
        public String toString() {
            return help == null ? line() : line() + '\n' + help;
        }
    }

}
