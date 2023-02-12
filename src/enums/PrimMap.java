package enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public abstract class PrimMap<E extends Enum<E>, A, V> implements Map<E, V> {

    private static int size(Class<?> enumClass) {
        return enumClass.getEnumConstants().length;
    }

    private final Class<E> enumClass;
    protected final A array;

    private PrimMap(Class<E> enumClass, A array) {
        this.enumClass = enumClass;
        this.array = array;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size(enumClass);
    }

    @Override
    public boolean containsKey(Object key) {
        return key.getClass() == enumClass;
    }

    @Override
    public V get(Object key) {
        return containsKey(key) ? get(((Enum<E>) key).ordinal()) : null;
    }

    protected abstract V get(int index);

    @Override
    public V put(E key, V value) {
        int o = key.ordinal();
        V ret = get(o);
        set(o, value);
        return ret;
    }

    protected abstract void set(int index, V value);

    public void set(E key, V value) {
        set(key.ordinal(), value);
    }

    public void set(V value, E... keys) {
        for (E key : keys)
            set(key.ordinal(), value);
    }

    public void setAll(V value) {
        for (int i = size() - 1; i >= 0; i--)
            set(i, value);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public void putAll(Map<? extends E, ? extends V> map) {
        map.forEach(this::put);
    }

    @Override
    public Set<E> keySet() {
        return Set.of(enumClass.getEnumConstants());
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> ret = new ArrayList<>(size());
        copyValues(ret);
        return ret;
    }

    protected abstract void copyValues(ArrayList<V> list);

    @Override
    public Set<Entry<E, V>> entrySet() {
        EnumMap<E, V> map = new EnumMap<>(enumClass);
        for (E e : enumClass.getEnumConstants())
            map.put(e, get(e.ordinal()));
        return map.entrySet();
    }

    public static class Bool<E extends Enum<E>> extends PrimMap<E, boolean[], Boolean> {

        public Bool(Class<E> enumClass) {
            super(enumClass, new boolean[size(enumClass)]);
        }

        public boolean getBool(E key) {
            return array[key.ordinal()];
        }

        @Override
        protected Boolean get(int index) {
            return array[index];
        }

        @Override
        protected void set(int index, Boolean value) {
            array[index] = value;
        }

        @Override
        public Boolean remove(Object key) {
            return containsKey(key) ? put((E) key, false) : null;
        }

        @Override
        public void clear() {
            setAll(false);
        }

        @Override
        public void copyValues(ArrayList<Boolean> list) {
            for (boolean bool : array)
                list.add(bool);
        }

    }

    private static abstract class Num<E extends Enum<E>, A> extends PrimMap<E, A, Number> {

        public Num(Class<E> enumClass, A array) {
            super(enumClass, array);
        }

        @Override
        public Number remove(Object key) {
            return containsKey(key) ? put((E) key, 0) : null;
        }

        @Override
        public void clear() {
            setAll(0);
        }

    }

    public static class Int<E extends Enum<E>> extends Num<E, int[]> {

        public Int(Class<E> enumClass) {
            super(enumClass, new int[size(enumClass)]);
        }

        public int getInt(E key) {
            return array[key.ordinal()];
        }

        @Override
        protected Number get(int index) {
            return array[index];
        }

        @Override
        protected void set(int index, Number value) {
            array[index] = (int) value;
        }

        @Override
        public void copyValues(ArrayList<Number> list) {
            for (int i : array)
                list.add(i);
        }

    }

    public static class Byte<E extends Enum<E>> extends Num<E, byte[]> {

        public Byte(Class<E> enumClass) {
            super(enumClass, new byte[size(enumClass)]);
        }

        public byte getByte(E key) {
            return array[key.ordinal()];
        }

        @Override
        protected Number get(int index) {
            return array[index];
        }

        @Override
        protected void set(int index, Number value) {
            array[index] = (byte) value;
        }

        @Override
        public void copyValues(ArrayList<Number> list) {
            for (byte i : array)
                list.add(i);
        }

    }

}
