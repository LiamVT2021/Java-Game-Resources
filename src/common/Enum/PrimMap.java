package common.Enum;

import static common.Builders.buildStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.util.Vacuous;

public abstract class PrimMap<E extends Enum<E>, A, V> implements Map<E, V> {

    private final Class<E> enumClass;
    protected A array;

    private PrimMap(Class<E> enumClass, IntFunction<A> arrGen) {
        this.enumClass = enumClass;
        clear(arrGen);
    }

    protected E[] keys() {
        return enumClass.getEnumConstants();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return keys().length;
    }

    @Override
    public boolean containsKey(Object key) {
        return key.getClass() == enumClass;
    }

    @Override
    public V get(Object key) {
        return containsKey(key) ? get(((E) key).ordinal()) : null;
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

    protected V removeHelp(Object key, V base) {
        return containsKey(key) ? put((E) key, base) : null;
    }

    protected void clear(IntFunction<A> arrGen) {
        array = arrGen.apply(size());
    }

    @Override
    public void putAll(Map<? extends E, ? extends V> map) {
        map.forEach(this::put);
    }

    @Override
    public Set<E> keySet() {
        return Set.of(keys());
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> ret = new ArrayList<>(size());
        forEach((e, v) -> ret.add(v));
        return ret;
    }

    @Override
    public Set<Entry<E, V>> entrySet() {
        EnumMap<E, V> map = new EnumMap<>(enumClass);
        forEach(map::put);
        return map.entrySet();
    }

    @Override
    public void forEach(BiConsumer<? super E, ? super V> func) {
        E[] keys = keys();
        for (int i = 0; i < keys.length; i++)
            func.accept(keys[i], get(i));
    }

    public <R> Stream<R> mapStream(BiFunction<? super E, ? super V, ? extends R> func) {
        return buildStream(builder -> forEach((e, v) -> builder.accept(func.apply(e, v))));
    }

    public static class Bool<E extends Enum<E>> extends PrimMap<E, boolean[], Boolean> {

        public Bool(Class<E> enumClass) {
            super(enumClass, boolean[]::new);
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
            return removeHelp(key, false);
        }

        @Override
        public void clear() {
            clear(boolean[]::new);
        }

        public void ifElse(Consumer<? super E> ifTrue, Consumer<? super E> ifFalse) {
            E[] keys = keys();
            for (int i = 0; i < keys.length; i++) {
                Consumer<? super E> func = get(i) ? ifTrue : ifFalse;
                func.accept(keys[i]);
            }
        }

        public Stream<E> trueStream() {
            return buildStream(builder -> ifElse(builder::accept, Vacuous::NOOP));
        }

        public Stream<E> falseStream() {
            return buildStream(builder -> ifElse(Vacuous::NOOP, builder::accept));
        }

    }

    private static abstract class Num<E extends Enum<E>, A, R extends Number> extends PrimMap<E, A, R> {

        public Num(Class<E> enumClass, IntFunction<A> array) {
            super(enumClass, array);
        }

        public IntStream intStream(ToIntBiFunction<E, Number> func) {
            IntStream.Builder builder = IntStream.builder();
            forEach((e, v) -> builder.accept(func.applyAsInt(e, v)));
            return builder.build();
        }

        public int sum(ToIntBiFunction<E, Number> func) {
            return intStream(func).sum();
        }

        public int product(ToIntBiFunction<E, Number> func) {
            return intStream(func).reduce(1, (a, b) -> a * b);
        }

    }

    public static class Int<E extends Enum<E>> extends Num<E, int[], Integer> {

        public Int(Class<E> enumClass) {
            super(enumClass, int[]::new);
        }

        public int getInt(E key) {
            return array[key.ordinal()];
        }

        @Override
        protected Integer get(int index) {
            return array[index];
        }

        @Override
        protected void set(int index, Integer value) {
            array[index] = value;
        }

        @Override
        public Integer remove(Object key) {
            return removeHelp(key, 0);
        }

        @Override
        public void clear() {
            clear(int[]::new);
        }

    }

    public static class Byte<E extends Enum<E>> extends Num<E, byte[], java.lang.Byte> {

        public Byte(Class<E> enumClass) {
            super(enumClass, byte[]::new);
        }

        public byte getByte(E key) {
            return array[key.ordinal()];
        }

        @Override
        protected java.lang.Byte get(int index) {
            return array[index];
        }

        @Override
        protected void set(int index, java.lang.Byte value) {
            array[index] = value;
        }

        @Override
        public java.lang.Byte remove(Object key) {
            return removeHelp(key, (byte) 0);
        }

        @Override
        public void clear() {
            clear(byte[]::new);
        }

    }

}
