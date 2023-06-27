package common.prim;

import common.prim.array.*;

/**
 * Using an indexOf method, allows use of PrimArray as a map.
 * 
 * @version 6/27/23
 */
public interface PrimMap<K, V extends Number> extends PrimArray<V> {

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

    static abstract class Byte<K> extends ByteArray implements PrimMap<K, java.lang.Byte> {

        public Byte(int size) {
            super(size);
        }

    }

    static abstract class Short<K> extends ShortArray implements PrimMap<K, java.lang.Short> {

        public Short(int size) {
            super(size);
        }

    }

    static abstract class Int<K> extends IntArray implements PrimMap<K, Integer> {

        public Int(int size) {
            super(size);
        }

    }

    static abstract class Long<K> extends LongArray implements PrimMap<K, java.lang.Long> {

        public Long(int size) {
            super(size);
        }

    }

    static abstract class Float<K> extends FloatArray implements PrimMap<K, java.lang.Float> {

        public Float(int size) {
            super(size);
        }

    }

    static abstract class Double<K> extends DoubleArray implements PrimMap<K, java.lang.Double> {

        public Double(int size) {
            super(size);
        }

    }

}
