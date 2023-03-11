package common.prim;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public abstract class Mapper<K, N extends Number, F, R, S> {

    protected final PrimMap<K, ?, N> map;
    protected final F func;

    protected Mapper(PrimMap<K, ?, N> map, F func) {
        this.map = map;
        this.func = func;
    }

    public abstract R array();

    public abstract S all();

    public abstract S stream(Stream<K> keys);

    public S of(K... keys) {
        return stream(Stream.of(keys));
    }

    public S of(Collection<K> keys) {
        return stream(keys.stream());
    }

    public S when(Predicate<K> pred) {
        return stream(map.keys().stream().filter(pred));
    }

    public static class Int<K, N extends Number>
            extends Mapper<K, N, ToIntBiFunction<? super K, ? super N>, int[], IntStream> {

        protected Int(PrimMap<K, ?, N> map, ToIntBiFunction<? super K, ? super N> func) {
            super(map, func);
        }

        @Override
        public int[] array() {
            List<K> keys = map.keys();
            int[] ret = new int[keys.size()];
            for (int i = 0; i < ret.length; i++)
                ret[i] = func.applyAsInt(keys.get(i), map.get(i));
            return ret;
        }

        @Override
        public IntStream all() {
            return IntStream.of(array());
        }

        @Override
        public IntStream stream(Stream<K> keys) {
            return keys.mapToInt(k -> func.applyAsInt(k, map.get(k)));
        }

    }

    public static class Long<K, N extends Number>
            extends Mapper<K, N, ToLongBiFunction<? super K, ? super N>, long[], LongStream> {

        protected Long(PrimMap<K, ?, N> map, ToLongBiFunction<? super K, ? super N> func) {
            super(map, func);
        }

        @Override
        public long[] array() {
            List<K> keys = map.keys();
            long[] ret = new long[keys.size()];
            for (int i = 0; i < ret.length; i++)
                ret[i] = func.applyAsLong(keys.get(i), map.get(i));
            return ret;
        }

        @Override
        public LongStream all() {
            return LongStream.of(array());
        }

        @Override
        public LongStream stream(Stream<K> keys) {
            return keys.mapToLong(k -> func.applyAsLong(k, map.get(k)));
        }

    }

    public static class Double<K, N extends Number>
            extends Mapper<K, N, ToDoubleBiFunction<? super K, ? super N>, double[], DoubleStream> {

        protected Double(PrimMap<K, ?, N> map, ToDoubleBiFunction<? super K, ? super N> func) {
            super(map, func);
        }

        @Override
        public double[] array() {
            List<K> keys = map.keys();
            double[] ret = new double[keys.size()];
            for (int i = 0; i < ret.length; i++)
                ret[i] = func.applyAsDouble(keys.get(i), map.get(i));
            return ret;
        }

        @Override
        public DoubleStream all() {
            return DoubleStream.of(array());
        }

        @Override
        public DoubleStream stream(Stream<K> keys) {
            return keys.mapToDouble(k -> func.applyAsDouble(k, map.get(k)));
        }

    }

    public static class Generic<K, N extends Number, R>
            extends Mapper<K, N, BiFunction<? super K, ? super N, R>, Object[], Stream<R>> {

        protected Generic(PrimMap<K, ?, N> map, BiFunction<? super K, ? super N, R> func) {
            super(map, func);
        }

        @Override
        public Object[] array() {
            return all().toArray();
        }

        @Override
        public Stream<R> all() {
            return stream(map.keys().stream());
        }

        @Override
        public Stream<R> stream(Stream<K> keys) {
            return keys.map(k -> func.apply(k, map.get(k)));
        }

    }

}
