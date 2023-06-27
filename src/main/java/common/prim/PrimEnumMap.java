package common.prim;

public interface PrimEnumMap<E extends Enum<E>, N extends Number, A> extends PrimMap<E, N, A> {

    @Override
    default int indexOf(E key) {
        return key.ordinal();
    }

    static class Int<E extends Enum<E>> extends PrimMap.Int<E> implements PrimEnumMap<E, Integer, int[]> {

        public Int(Class<E> clazz) {
            super(clazz.getEnumConstants().length);
        }

    }

}
