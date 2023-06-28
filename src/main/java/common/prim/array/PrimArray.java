package common.prim.array;

import java.util.stream.Stream;

import common.pushPop.ArrayWrapper;

/**
 * Wrapper interface around an array of primitive numbers.
 * 
 * @param N the type of numbers returned by get methods.
 * @param A the type of the wrapped array.
 * @version 6/28/23
 */
public interface PrimArray<N extends Number, A> extends ArrayWrapper<N, Number, A> {

    @Override
    default Stream<N> stream() {
        Stream.Builder<N> builder = Stream.builder();
        forEach(builder);
        return builder.build();
    }

    static abstract class ADT<N extends Number, A> extends ArrayWrapper.ADT<N, Number, A> implements PrimArray<N, A> {

        public ADT(A array) {
            super(array);
        }

    }

}
