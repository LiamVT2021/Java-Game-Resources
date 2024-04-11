package common.prim;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.prim.array.*;

/**
 * Using an indexOf method, allows use of PrimArray as a map.
 * 
 * @param <K> the type of the keys
 * @param <V> the type of the values
 * @param <A> the type of the wrapped array
 * @version 4/11/24
 */
public interface PrimMap<K, V extends Number, A> extends PrimArray<V, A> {

    /**
     * @return the index coresponding to this key
     *         will be out of bounds if invalid key
     */
    int indexOf(K key);

    /**
     * @return the value stored at key
     * @throws IndexOutOfBoundsException if invalid key
     */
    default V get(K key) {
        return get(indexOf(key));
    }

    /**
     * @return the values stored at keys
     * @throws IndexOutOfBoundsException if any invalid keys
     */
    default Stream<V> get(@SuppressWarnings("unchecked") K... keys) {
        return get(Stream.of(keys));
    }

    /**
     * @return the values stored at keys
     * @throws IndexOutOfBoundsException if any invalid keys
     */
    default Stream<V> get(Stream<K> keys) {
        return keys.map(this::get);
    }

    /**
     * Stores value at key.
     * 
     * @throws IndexOutOfBoundsException if invalid key
     */
    default void set(K key, V value) {
        set(indexOf(key), value);
    }

    /**
     * sets value for all provided keys
     */
    default void setAll(V value, @SuppressWarnings("unchecked") K... keys) {
        for (K key : keys)
            set(key, value);
    }

    /**
     * sets value for all provided keys
     */
    default void setAll(V value, Stream<K> keys) {
        setAll(value, keys.mapToInt(this::indexOf));
    }

    /**
     * Performs a set and get at the same time.
     * Stores value at key.
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

    /**
     * @param <R>  the type of returned objects
     * @param func a BiFunction for mapping key value pairs into returned objects
     * @return A Stream of returned objects
     * @throws IndexOutOfBoundsException if any invalid keys
     */
    default <R> Stream<R> map(BiFunction<K, V, R> func, @SuppressWarnings("unchecked") K... keys) {
        return map(Stream.of(keys), func);
    }

    /**
     * @param <R>  the type of returned objects
     * @param func a BiFunction for mapping key value pairs into returned objects
     * @return A Stream of returned objects
     * @throws IndexOutOfBoundsException if any invalid keys
     */
    default <R> Stream<R> map(Stream<K> keys, BiFunction<K, V, R> func) {
        return keys.map(key -> func.apply(key, get(key)));
    }

    /**
     * @return A String representation of specified key value pairs
     *         with format "Key: Value"
     * @throws IndexOutOfBoundsException if any invalid keys
     */
    default String mapString(@SuppressWarnings("unchecked") K... keys) {
        return mapString(Stream.of(keys));
    }

    /**
     * @return A String representation of specified key value pairs
     *         with format "Key: Value"
     * @throws IndexOutOfBoundsException if any invalid keys
     */
    default String mapString(Stream<K> keys) {
        return map(keys, (k, v) -> k + ": " + v).collect(Collectors.joining("\n"));
    }

    /**
     * modifies the value stored at key using the modifier
     * 
     * @return the modified value
     */
    default V modify(K key, UnaryOperator<V> modifier) {
        return modify(indexOf(key), modifier);
    }

    /**
     * modifies value for all provided keys using the modifier
     */
    default void modifyAll(UnaryOperator<V> modifier, @SuppressWarnings("unchecked") K... keys) {
        for (K key : keys) {
            int index = indexOf(key);
            set(index, modifier.apply(get(index)));
        }
    }

    /**
     * modifies value for all provided keys using the modifier
     */
    default void modifyAll(UnaryOperator<V> modifier, Stream<K> keys) {
        modifyAll(modifier, keys.mapToInt(this::indexOf));
    }

    /**
     * adds modifier to the value at key
     * 
     * @return the sum
     */
    default V add(K key, Number modifier) {
        return add(indexOf(key), modifier);
    }

    /**
     * adds modifier to value for all provided keys
     */
    default void addAll(Number modifier, @SuppressWarnings("unchecked") K... keys) {
        modifyAll(n -> sum(n, modifier), keys);
    }

    /**
     * adds modifier to value for all provided keys
     */
    default void addAll(Number modifier, Stream<K> keys) {
        addAll(modifier, keys.mapToInt(this::indexOf));
    }

    /**
     * multiplys the value at key by modifier
     *
     * @return the product
     */
    default V multiply(K key, Number modifier) {
        return multiply(indexOf(key), modifier);
    }

    /**
     * multiplys the value by modifier for all provided keys
     */
    default void multiplyAll(Number modifier, @SuppressWarnings("unchecked") K... keys) {
        modifyAll(n -> product(n, modifier), keys);
    }

    /**
     * multiplys the value by modifier for all provided keys
     */
    default void multiplyAll(Number modifier, Stream<K> keys) {
        multiplyAll(modifier, keys.mapToInt(this::indexOf));
    }

    static abstract class ByteMap<K> extends ByteArray implements PrimMap<K, Byte, byte[]> {

        public ByteMap(int size) {
            super(size);
        }

    }

    static abstract class ShortMap<K> extends ShortArray implements PrimMap<K, Short, short[]> {

        public ShortMap(int size) {
            super(size);
        }

    }

    static abstract class IntMap<K> extends IntArray implements PrimMap<K, Integer, int[]> {

        public IntMap(int size) {
            super(size);
        }

    }

    static abstract class LongMap<K> extends LongArray implements PrimMap<K, Long, long[]> {

        public LongMap(int size) {
            super(size);
        }

    }

    static abstract class FloatMap<K> extends FloatArray implements PrimMap<K, Float, float[]> {

        public FloatMap(int size) {
            super(size);
        }

    }

    static abstract class DoubleMap<K> extends DoubleArray implements PrimMap<K, Double, double[]> {

        public DoubleMap(int size) {
            super(size);
        }

    }

}
