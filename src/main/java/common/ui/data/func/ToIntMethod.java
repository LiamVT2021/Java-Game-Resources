package common.ui.data.func;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import common.ui.data.adt.Describable;

/**
 * A ToIntFunction with a description
 * 
 * @param I the input type of this Method
 * @version 3/1/25
 */
public final class ToIntMethod<I> extends Describable.ADT implements Method.Int<I> {
    private final Function<I, String> calc;
    private final ToIntFunction<I> func;

    /**
     * @param description one line description of this ToIntFunction
     * @param predicate
     */
    public ToIntMethod(String description, ToIntFunction<I> function) {
        this(description, null, null, function);
    }

    /**
     * @param line     one line description of this ToIntFunction
     * @param full     longer description of this ToIntFunction
     * @param calc     function for displaying the calculation
     * @param function
     */
    public ToIntMethod(String line, String full, Function<I, String> calc, ToIntFunction<I> function) {
        super(line, full);
        this.calc = calc;
        func = function;
    }

    @Override
    public int applyAsInt(I input) {
        return func.applyAsInt(input);
    }

    @Override
    public Result<Integer> results(I ipnut) {
        return new IntResult(ipnut);
    }

    private class IntResult extends Result<Integer> {
        private final String calc;
        private final int result;

        public IntResult(I ipnut) {
            calc = ToIntMethod.this.calc == null ? null : ToIntMethod.this.calc.apply(ipnut);
            result = applyAsInt(ipnut);
        }

        @Override
        public String text() {
            return line;
        }

        @Override
        public String calc() {
            return calc;
        }

        @Override
        public Integer result() {
            return result;
        }

        @Override
        public String lineDescription() {
            return calc == null ? line + ": " + result : line + ": " + calc + " = " + result;
        }

        @Override
        public String fullDescription() {
            String line = lineDescription();
            return full == null ? line : line + '\n' + full;
        }
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof ToIntMethod && func.equals(((ToIntMethod<?>) other).func);
    }

    @Override
    public int hashCode() {
        return func.hashCode();
    }

}
