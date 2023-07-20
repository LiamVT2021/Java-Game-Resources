package common.prim;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@FunctionalInterface
public interface NumStream {

    Stream<? extends Number> numStream();

    default IntStream intStream() {
        return numStream().mapToInt(Number::intValue);
    }

    default int[] intArr() {
        return intStream().toArray();
    }

    default LongStream longStream() {
        return numStream().mapToLong(Number::longValue);
    }

    default long[] longArr() {
        return longStream().toArray();
    }

    default DoubleStream doubleStream() {
        return numStream().mapToDouble(Number::doubleValue);
    }

    default double[] doubleArr() {
        return doubleStream().toArray();
    }

}
