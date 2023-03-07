package common.prim;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;
import java.util.stream.Stream;

import common.Builders;

public interface PrimMap<E extends Enum<E>, A, N extends Number> extends PrimArr<A, N> {

    E[] keys();

    default N get(E key) {
        return get(key.ordinal());
    }

    default void set(E key, N value) {
        set(key.ordinal(), value);
    }

    default <R> R map(E key, BiFunction<? super E, ? super N, ? extends R> func) {
        return func.apply(key, get(key));
    }

    default Stream<N> stream(E... keys) {
        return Stream.of(keys).map(this::get);
    }

    default Map<E, N> map() {
        return Builders.buildMap(map -> forEach((e, n) -> map.put(e, n)));
    }

    // ForEach methods

    default void forEach(BiConsumer<? super E, ? super N> func) {
        E[] keys = keys();
        for (int i = 0; i < keys.length; i++)
            func.accept(keys[i], get(i));
    }

    default void forEach(BiConsumer<? super E, ? super N> func, Stream<E> keys) {
        keys.forEach(k -> func.accept(k, get(k)));
    }

    default void forEach(BiConsumer<? super E, ? super N> func, E... keys) {
        forEach(func, Stream.of(keys));
    }

    default void forEach(BiConsumer<? super E, ? super N> func, Collection<E> keys) {
        forEach(func, keys.stream());
    }

    default void forEach(BiConsumer<? super E, ? super N> func, Predicate<E> pred) {
        forEach(func, Stream.of(keys()).filter(pred));
    }

    // Mappers

    public default Mapper.Int<E, N> mapToInt(ToIntBiFunction<? super E, ? super N> func) {
        return new Mapper.Int<>(this, func);
    }

    public default Mapper.Long<E, N> mapToLong(ToLongBiFunction<? super E, ? super N> func) {
        return new Mapper.Long<>(this, func);
    }

    public default Mapper.Double<E, N> mapToDouble(ToDoubleBiFunction<? super E, ? super N> func) {
        return new Mapper.Double<>(this, func);
    }

    public default <R> Mapper.Generic<E, N, R> map(BiFunction<? super E, ? super N, R> func) {
        return new Mapper.Generic<>(this, func);
    }

    // Implementations

    static class Int<E extends Enum<E>> extends PrimArr.Int implements PrimMap<E, int[], Integer> {

        private final Class<E> clazz;

        protected Int(Class<E> clazz, int length) {
            super(length);
            this.clazz = clazz;
        }

        public Int(Class<E> clazz) {
            this(clazz, clazz.getEnumConstants().length);
        }

        @Override
        public E[] keys() {
            return clazz.getEnumConstants();
        }

    }

    static class Byte<E extends Enum<E>> extends PrimArr.Byte implements PrimMap<E, byte[], java.lang.Byte> {

        private final Class<E> clazz;

        protected Byte(Class<E> clazz, int length) {
            super(length);
            this.clazz = clazz;
        }

        public Byte(Class<E> clazz) {
            this(clazz, clazz.getEnumConstants().length);
        }

        @Override
        public E[] keys() {
            return clazz.getEnumConstants();
        }

    }

    static class Float<E extends Enum<E>> extends PrimArr.Float implements PrimMap<E, float[], java.lang.Float> {

        private final Class<E> clazz;

        protected Float(Class<E> clazz, int length) {
            super(length);
            this.clazz = clazz;
        }

        public Float(Class<E> clazz) {
            this(clazz, clazz.getEnumConstants().length);
        }

        @Override
        public E[] keys() {
            return clazz.getEnumConstants();
        }

        public float getProduct(E key, int input) {
            return array[key.ordinal()] * input;
        }

    }

}
