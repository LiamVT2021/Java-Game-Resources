package common.prim;

public interface PrimSubMap<E extends Enum<E>, A, N extends Number> extends PrimMap<E, A, N> {

    int offset();

    @Override
    default N get(E key) {
        return get(key.ordinal() - offset());
    }

    @Override
    default void set(E key, N value) {
        set(key.ordinal() - offset(), value);
    }

    static class Int<E extends Enum<E>> extends PrimMap.Int<E> implements PrimSubMap<E, int[], Integer> {

        private final int offset;

        public Int(Class<E> clazz, E start, E end) {
            super(clazz, end.ordinal() - start.ordinal() + 1);
            offset = end.ordinal();
        }

        @Override
        public int offset() {
            return offset;
        }

    }

    static class Byte<E extends Enum<E>> extends PrimMap.Byte<E> implements PrimSubMap<E, byte[], java.lang.Byte> {

        private final int offset;

        public Byte(Class<E> clazz, E start, E end) {
            super(clazz, end.ordinal() - start.ordinal() + 1);
            offset = end.ordinal();
        }

        @Override
        public int offset() {
            return offset;
        }

    }

}
