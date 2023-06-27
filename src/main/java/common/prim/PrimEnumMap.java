package common.prim;

public interface PrimEnumMap<E extends Enum<E>, N extends Number> extends PrimMap<E, N> {

    @Override
    default int indexOf(E key) {
        return key.ordinal();
    }

    static class Int<E extends Enum<E>> extends PrimMap.Int<E> implements PrimEnumMap<E, Integer> {

        public Int(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

}
