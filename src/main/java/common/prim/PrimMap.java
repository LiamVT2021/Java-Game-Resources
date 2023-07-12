package common.prim;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.prim.array.*;

/**
 * Using an indexOf method, allows use of PrimArray as a map.
 * 
 * @version 6/27/23
 */
public interface PrimMap<K, V extends Number, A> extends PrimArray<V, A> {

    /**
     * @return the index coresponding to this key
     *         will be out of bounds if invalid key
     */
    int indexOf(K key);

    /**
     * @return the values stored at key
     * @throws IndexOutOfBoundsException if invalid key
     */
    default V get(K key) {
        return get(indexOf(key));
    }

    @SuppressWarnings("unchecked")
    default Stream<V> get(K... keys) {
        return get(Stream.of(keys));
    }

    default Stream<V> get(Stream<K> keys) {
        return keys.map(this::get);
    }

    /**
     * Stores value at key
     * 
     * @throws IndexOutOfBoundsException if invalid key
     */
    default void set(K key, V value) {
        set(indexOf(key), value);
    }

    /**
     * performs a set and get at the same time
     * Stores value at key
     * 
     * @return the value that was stored at key
     * @throws IndexOutOfBoundsException if invalid key
     */
    default V swap(K key, V value) {
        int index = indexOf(key);
        V ret = get(index);
        set(index, value);
        return ret;
    }

    @SuppressWarnings("unchecked")
    default <R> Stream<R> map(BiFunction<K, V, R> func, K... keys) {
        return map(Stream.of(keys), func);
    }

    default <R> Stream<R> map(Stream<K> keys, BiFunction<K, V, R> func) {
        return keys.map(key -> func.apply(key, get(key)));
    }

    @SuppressWarnings("unchecked")
    default String mapString(K... keys) {
        return mapString(Stream.of(keys));
    }

    default String mapString(Stream<K> keys) {
        return map(keys, (k, v) -> k + ": " + v).collect(Collectors.joining("\n"));
    }

    static abstract class Byte<K> extends ByteArray implements PrimMap<K, java.lang.Byte, byte[]> {

        public Byte(int size) {
            super(size);
        }

    }

    static abstract class Short<K> extends ShortArray implements PrimMap<K, java.lang.Short, short[]> {

        public Short(int size) {
            super(size);
        }

    }

    static abstract class Int<K> extends IntArray implements PrimMap<K, Integer, int[]> {

        public Int(int size) {
            super(size);
        }

    }

    static abstract class Long<K> extends LongArray implements PrimMap<K, java.lang.Long, long[]> {

        public Long(int size) {
            super(size);
        }

    }

    static abstract class Float<K> extends FloatArray implements PrimMap<K, java.lang.Float, float[]> {

        public Float(int size) {
            super(size);
        }

    }

    static abstract class Double<K> extends DoubleArray implements PrimMap<K, java.lang.Double, double[]> {

        public Double(int size) {
            super(size);
        }

    }

}
