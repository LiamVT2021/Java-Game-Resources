package common.ui.data.func;

import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Predicate visible to the user
 * 
 * @param I the input type of this Predicate
 */
public interface BoolMethod<I> extends Method<I, Boolean>, Predicate<I> {

    @Override
    default Boolean apply(I input) {
        return test(input);
    }

    default Filter<I> filter(I[] input, IntFunction<I[]> arrFunc) {
        Result<?, ?, ?>[] children = Stream.of(input).map(this::results).toArray(Result[]::new);
        I[] output = Stream.of(children).filter(r -> (boolean) r.output).toArray(arrFunc);
        return new Filter<>(this, input, output, children);
    }

    class Filter<I> extends Result<BoolMethod<I>, I[], I[]> {
        private final BoolMethod<I> method;

        public Filter(BoolMethod<I> method, I[] input, I[] output, Result<?, ?, ?>[] children) {
            super(input, output.length + "/" + input.length, output, children);
            this.method = method;
        }

        @Override
        public BoolMethod<I> method() {
            return method;
        }
    }

}
