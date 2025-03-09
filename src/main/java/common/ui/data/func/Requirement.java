package common.ui.data.func;

import java.util.function.Predicate;

/**
 * wrapper to make a Predicate visible to the user
 * 
 * @param I the input type of this Method
 * @version 3/8/25
 */
public final class Requirement<I> extends MethodWrap<I, Predicate<I>, Boolean> implements Method.Bool<I> {
    /**
     * @param description one line description of this Predicate
     * @param predicate
     */
    public Requirement(String description, Predicate<I> predicate) {
        this(description, null, predicate);
    }

    /**
     * @param description one line description of this Predicate
     * @param helpText    additional information about this this Predicate
     * @param predicate
     */
    public Requirement(String description, String helpText, Predicate<I> predicate) {
        super(description, helpText, predicate);
    }

    @Override
    public boolean test(I input) {
        return func.test(input);
    }

    @Override
    public Result<I, Requirement<I>, Boolean> results(I input) {
        return new ReqResult(input);
    }

    private final class ReqResult extends WrapResult<Requirement<I>> {
        private ReqResult(I input) {
            super(input);
        }
    }

}
