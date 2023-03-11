package common.prim;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;
import java.util.stream.Stream;

import common.Builders;

public interface PrimMap<K, A, N extends Number> extends PrimArr<A, N> {

    int index(K key);

    List<K> keys();

    default N get(K key) {
        return get(index(key));
    }

    default void set(K key, N value) {
        set(index(key), value);
    }

    default <R> R map(K key, BiFunction<? super K, ? super N, ? extends R> func) {
        return func.apply(key, get(key));
    }

    default Stream<N> stream(K... keys) {
        return Stream.of(keys).map(this::get);
    }

    default Map<K, N> map() {
        return Builders.buildMap(map -> forEach((e, n) -> map.put(e, n)));
    }

    // ForEach methods

    default void forEach(BiConsumer<? super K, ? super N> func) {
        List<K> keys = keys();
        for (int i = 0; i < keys.size(); i++)
            func.accept(keys.get(i), get(i));
    }

    default void forEach(BiConsumer<? super K, ? super N> func, Stream<K> keys) {
        keys.forEach(k -> func.accept(k, get(k)));
    }

    default void forEach(BiConsumer<? super K, ? super N> func, K... keys) {
        forEach(func, Stream.of(keys));
    }

    default void forEach(BiConsumer<? super K, ? super N> func, Collection<K> keys) {
        forEach(func, keys.stream());
    }

    default void forEach(BiConsumer<? super K, ? super N> func, Predicate<K> pred) {
        forEach(func, keys().stream().filter(pred));
    }

    // Mappers

    public default Mapper.Int<K, N> mapToInt(ToIntBiFunction<? super K, ? super N> func) {
        return new Mapper.Int<>(this, func);
    }

    public default Mapper.Long<K, N> mapToLong(ToLongBiFunction<? super K, ? super N> func) {
        return new Mapper.Long<>(this, func);
    }

    public default Mapper.Double<K, N> mapToDouble(ToDoubleBiFunction<? super K, ? super N> func) {
        return new Mapper.Double<>(this, func);
    }

    public default <R> Mapper.Generic<K, N, R> map(BiFunction<? super K, ? super N, R> func) {
        return new Mapper.Generic<>(this, func);
    }

}
