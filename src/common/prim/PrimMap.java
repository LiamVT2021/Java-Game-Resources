package common.prim;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
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

    default void forEach(BiConsumer<? super E, N> func) {
        E[] keys = keys();
        for (int i = 0; i < keys.length; i++)
            func.accept(keys[i], get(i));
    }

    default <R> Stream<R> map(BiFunction<? super E, N, ? extends R> func) {
        E[] keys = keys();
        return Builders.buildStream(builder -> {
            for (int i = 0; i < keys.length; i++)
                builder.accept(func.apply(keys[i], get(i)));
        });
    }

    default Map<E, N> map() {
        return Builders.buildMap(map -> forEach((e, n) -> map.put(e, n)));
    }

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
