package common.prim;

import java.util.List;

public interface EnumSubMap<E extends Enum<E>, A, N extends Number> extends PrimEnumMap<E, A, N> {

    int offset();

    @Override
    default int index(E key) {
        return key.ordinal() - offset();
    }

    @Override
    default List<E> keys() {
        return PrimEnumMap.super.keys().subList(offset(), offset() + length());
    }

    static class Int<E extends Enum<E>> extends PrimEnumMap.Int<E> implements EnumSubMap<E, int[], Integer> {

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

    static class Byte<E extends Enum<E>> extends PrimEnumMap.Byte<E> implements EnumSubMap<E, byte[], java.lang.Byte> {

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
