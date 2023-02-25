package common.Enum;

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

    default <R> R map(E key, BiFunction<? super E, N, ? extends R> func) {
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

        public Int(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
            this.clazz = clazz;
        }

        @Override
        public E[] keys() {
            return clazz.getEnumConstants();
        }

    }

    static class Byte<E extends Enum<E>> extends PrimArr.Byte implements PrimMap<E, byte[], java.lang.Byte> {

        private final Class<E> clazz;

        public Byte(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
            this.clazz = clazz;
        }

        @Override
        public E[] keys() {
            return clazz.getEnumConstants();
        }

    }

}
