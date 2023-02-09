package cache;

import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntCache extends Cache<Integer> {

    public Key base(String str) {
        return new Base(str, 0);
    }

    public Key intFunc(String str, ToIntFunction<IntStream> func, Key... keys) {
        return new Func(str, i -> func.applyAsInt(Stream.of(keys).mapToInt(i::calc)));
    }

    public Key sum(String str, Key... keys) {
        return intFunc(str, IntStream::sum, keys);
    }

    public class XP extends Key {
        private final Key xp;
        private final IntUnaryOperator xpFunc;

        public XP(String str, IntUnaryOperator xpFunc) {
            super(str);
            xp = base(str + "_XP");
            this.xpFunc = xpFunc;
        }

        @Override
        public Integer get(Cache<Integer>.Instance cache) {
            return xpFunc.applyAsInt(xp.get(cache));
        }
    }

}