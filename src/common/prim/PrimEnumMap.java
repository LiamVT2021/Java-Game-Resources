package common.prim;

import java.util.List;

public interface PrimEnumMap<E extends Enum<E>, A, N extends Number> extends PrimMap<E, A, N> {

    Class<E> keyClass();

    @Override
    default int index(E key) {
        return key.ordinal();
    }

    @Override
    default List<E> keys() {
        return List.of(keyClass().getEnumConstants());
    }

    // Implementations

    static class Int<E extends Enum<E>> extends PrimArr.Int implements PrimEnumMap<E, int[], Integer> {

        private final Class<E> clazz;

        protected Int(Class<E> clazz, int length) {
            super(length);
            this.clazz = clazz;
        }

        public Int(Class<E> clazz) {
            this(clazz, clazz.getEnumConstants().length);
        }

        @Override
        public Class<E> keyClass() {
            return clazz;
        }

    }

    static class Short<E extends Enum<E>> extends PrimArr.Short implements PrimEnumMap<E, short[], java.lang.Short> {

        private final Class<E> clazz;

        protected Short(Class<E> clazz, int length) {
            super(length);
            this.clazz = clazz;
        }

        public Short(Class<E> clazz) {
            this(clazz, clazz.getEnumConstants().length);
        }

        @Override
        public Class<E> keyClass() {
            return clazz;
        }

    }

    static class Byte<E extends Enum<E>> extends PrimArr.Byte implements PrimEnumMap<E, byte[], java.lang.Byte> {

        private final Class<E> clazz;

        protected Byte(Class<E> clazz, int length) {
            super(length);
            this.clazz = clazz;
        }

        public Byte(Class<E> clazz) {
            this(clazz, clazz.getEnumConstants().length);
        }

        @Override
        public Class<E> keyClass() {
            return clazz;
        }

    }

    static class Float<E extends Enum<E>> extends PrimArr.Float implements PrimEnumMap<E, float[], java.lang.Float> {

        private final Class<E> clazz;

        protected Float(Class<E> clazz, int length) {
            super(length);
            this.clazz = clazz;
        }

        public Float(Class<E> clazz) {
            this(clazz, clazz.getEnumConstants().length);
        }

        @Override
        public Class<E> keyClass() {
            return clazz;
        }

        public float getProduct(E key, int input) {
            return array[key.ordinal()] * input;
        }

    }

}
