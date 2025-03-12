package common.ui.data.func;

import java.util.function.ToIntFunction;
import common.util.math.RelationalOperator;

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

    default BoolMethod<I> progress(RelationalOperator operator, int target) {
        return new Progress<>(this, operator, target);
    }

    static class Progress<I> implements BoolMethod<I> {
        private final IntMethod<I> method;
        private final RelationalOperator op;
        private final int target;

        public Progress(IntMethod<I> method, RelationalOperator operator, int target) {
            this.method = method;
            op = operator;
            this.target = target;
        }

        @Override
        public boolean test(I input) {
            return op.compare(method.applyAsInt(input), target);
        }

        @Override
        public Result.Bool<I> result(I input) {
            return new ProgResult(method.result(input));
        }

        private final class ProgResult extends Result.Bool<I> {
            private ProgResult(Result<?, I, Integer> result) {
                super(result.input, op.equation(result.output, target),
                        op.compare((int) result.output, target), result.children);
            }

            @Override
            public BoolMethod<I> method() {
                return Progress.this;
            }
        }
    }

}
