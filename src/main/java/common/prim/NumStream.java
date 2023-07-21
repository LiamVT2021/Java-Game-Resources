package common.prim;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@FunctionalInterface
public interface NumStream {

    /**
     * @return a Stream of Numbers
     */
    Stream<? extends Number> stream();

    /**
     * @return steam mapped to IntStream
     */
    default IntStream intStream() {
        return stream().mapToInt(Number::intValue);
    }

    /**
     * @return steam mapped to an int array
     */
    default int[] intArr() {
        return intStream().toArray();
    }

    /**
     * @return steam mapped to LongStream
     */
    default LongStream longStream() {
        return stream().mapToLong(Number::longValue);
    }

    /**
     * @return steam mapped to an long array
     */
    default long[] longArr() {
        return longStream().toArray();
    }

    /**
     * @return steam mapped to DoubleStream
     */
    default DoubleStream doubleStream() {
        return stream().mapToDouble(Number::doubleValue);
    }

    /**
     * @return steam mapped to an double array
     */
    default double[] doubleArr() {
        return doubleStream().toArray();
    }

}
