package common.prim;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public abstract class Mapper<E extends Enum<E>, N extends Number, F, R, S> {

    protected final PrimMap<E, ?, N> map;
    protected final F func;

    protected Mapper(PrimMap<E, ?, N> map, F func) {
        this.map = map;
        this.func = func;
    }

    abstract R array();

    abstract S all();

    abstract S stream(Stream<E> keys);

    S of(E... keys) {
        return stream(Stream.of(keys));
    }

    S of(Collection<E> keys) {
        return stream(keys.stream());
    }

    S when(Predicate<E> pred) {
        return stream(Stream.of(map.keys()).filter(pred));
    }

    public static class Int<E extends Enum<E>, N extends Number>
            extends Mapper<E, N, ToIntBiFunction<? super E, ? super N>, int[], IntStream> {

        protected Int(PrimMap<E, ?, N> map, ToIntBiFunction<? super E, ? super N> func) {
            super(map, func);
        }

        @Override
        int[] array() {
            E[] keys = map.keys();
            int[] ret = new int[keys.length];
            for (int i = 0; i < keys.length; i++)
                ret[i] = func.applyAsInt(keys[i], map.get(i));
            return ret;
        }

        @Override
        IntStream all() {
            return IntStream.of(array());
        }

        @Override
        IntStream stream(Stream<E> keys) {
            return keys.mapToInt(k -> func.applyAsInt(k, map.get(k)));
        }

    }

    public static class Long<E extends Enum<E>, N extends Number>
            extends Mapper<E, N, ToLongBiFunction<? super E, ? super N>, long[], LongStream> {

        protected Long(PrimMap<E, ?, N> map, ToLongBiFunction<? super E, ? super N> func) {
            super(map, func);
        }

        @Override
        long[] array() {
            E[] keys = map.keys();
            long[] ret = new long[keys.length];
            for (int i = 0; i < keys.length; i++)
                ret[i] = func.applyAsLong(keys[i], map.get(i));
            return ret;
        }

        @Override
        LongStream all() {
            return LongStream.of(array());
        }

        @Override
        LongStream stream(Stream<E> keys) {
            return keys.mapToLong(k -> func.applyAsLong(k, map.get(k)));
        }

    }

    public static class Double<E extends Enum<E>, N extends Number>
            extends Mapper<E, N, ToDoubleBiFunction<? super E, ? super N>, double[], DoubleStream> {

        protected Double(PrimMap<E, ?, N> map, ToDoubleBiFunction<? super E, ? super N> func) {
            super(map, func);
        }

        @Override
        double[] array() {
            E[] keys = map.keys();
            double[] ret = new double[keys.length];
            for (int i = 0; i < keys.length; i++)
                ret[i] = func.applyAsDouble(keys[i], map.get(i));
            return ret;
        }

        @Override
        DoubleStream all() {
            return DoubleStream.of(array());
        }

        @Override
        DoubleStream stream(Stream<E> keys) {
            return keys.mapToDouble(k -> func.applyAsDouble(k, map.get(k)));
        }

    }

    public static class Generic<E extends Enum<E>, N extends Number, R>
            extends Mapper<E, N, BiFunction<? super E, ? super N, R>, Object[], Stream<R>> {

        protected Generic(PrimMap<E, ?, N> map, BiFunction<? super E, ? super N, R> func) {
            super(map, func);
        }

        @Override
        Object[] array() {
            return all().toArray();
        }

        @Override
        Stream<R> all() {
            return stream(Stream.of(map.keys()));
        }

        @Override
        Stream<R> stream(Stream<E> keys) {
            return keys.map(k -> func.apply(k, map.get(k)));
        }

    }

}
