package common.prim;

/**
 * Implements PrimMap using the Enum's ordinal method as the index.
 * 
 * @param <E> the type of the keys
 * @param <V> the type of the values
 * @param <A> the type of the wrapped array
 * @version 7/12/23
 */
public interface PrimEnumMap<E extends Enum<E>, N extends Number, A> extends PrimMap<E, N, A> {

    @Override
    default int indexOf(E key) {
        return key.ordinal();
    }

    static class ByteEnumMap<E extends Enum<E>> extends ByteMap<E> implements PrimEnumMap<E, Byte, byte[]> {

        public ByteEnumMap(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

        public ByteEnumMap(Class<E> clazz, byte... values) {
            super(values);
            if (values.length != clazz.getEnumConstants().length)
                throw new IllegalArgumentException("Number of values does not match number of Attributes");
        }
    }

    static class ShortEnumMap<E extends Enum<E>> extends ShortMap<E> implements PrimEnumMap<E, Short, short[]> {

        public ShortEnumMap(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

        public ShortEnumMap(Class<E> clazz, short... values) {
            super(values);
            if (values.length != clazz.getEnumConstants().length)
                throw new IllegalArgumentException("Number of values does not match number of Attributes");
        }
    }

    static class IntEnumMap<E extends Enum<E>> extends IntMap<E> implements PrimEnumMap<E, Integer, int[]> {

        public IntEnumMap(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

        public IntEnumMap(Class<E> clazz, int... values) {
            super(values);
            if (values.length != clazz.getEnumConstants().length)
                throw new IllegalArgumentException("Number of values does not match number of Attributes");
        }
    }

    static class LongEnumMap<E extends Enum<E>> extends LongMap<E> implements PrimEnumMap<E, Long, long[]> {

        public LongEnumMap(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

        public LongEnumMap(Class<E> clazz, long... values) {
            super(values);
            if (values.length != clazz.getEnumConstants().length)
                throw new IllegalArgumentException("Number of values does not match number of Attributes");
        }
    }

    static class FloatEnumMap<E extends Enum<E>> extends FloatMap<E> implements PrimEnumMap<E, Float, float[]> {

        public FloatEnumMap(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

        public FloatEnumMap(Class<E> clazz, float... values) {
            super(values);
            if (values.length != clazz.getEnumConstants().length)
                throw new IllegalArgumentException("Number of values does not match number of Attributes");
        }
    }

    static class DoubleEnumMap<E extends Enum<E>> extends DoubleMap<E> implements PrimEnumMap<E, Double, double[]> {

        public DoubleEnumMap(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

        public DoubleEnumMap(Class<E> clazz, double... values) {
            super(values);
            if (values.length != clazz.getEnumConstants().length)
                throw new IllegalArgumentException("Number of values does not match number of Attributes");
        }

    }

}
