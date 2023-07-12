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

    static class Byte<E extends Enum<E>> extends PrimMap.Byte<E> implements PrimEnumMap<E, java.lang.Byte, byte[]> {

        public Byte(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

    static class Short<E extends Enum<E>> extends PrimMap.Short<E> implements PrimEnumMap<E, java.lang.Short, short[]> {

        public Short(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

    static class Int<E extends Enum<E>> extends PrimMap.Int<E> implements PrimEnumMap<E, Integer, int[]> {

        public Int(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

    static class Long<E extends Enum<E>> extends PrimMap.Long<E> implements PrimEnumMap<E, java.lang.Long, long[]> {

        public Long(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

    static class Float<E extends Enum<E>> extends PrimMap.Float<E> implements PrimEnumMap<E, java.lang.Float, float[]> {

        public Float(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

    static class Double<E extends Enum<E>> extends PrimMap.Double<E>
            implements PrimEnumMap<E, java.lang.Double, double[]> {

        public Double(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

}
