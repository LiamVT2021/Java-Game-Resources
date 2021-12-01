package heap;

public interface PrimStack extends PushPop.Prim, Expand {

    @Override
    public default boolean isEmpty() {
        return Expand.super.isEmpty();
    }

    @Override
    public default boolean push(int i) {
        if (!expand())
            return false;
        set(sizePP(), i);
        return true;
    }

    @Override
    public default int primPop() {
        return get(mmSize());
    }

    @Override
    public default int primPeek() {
        return get(size() - 1);
    }

    public void set(int index, int value);

    public int get(int index);

    public static class Int extends Expand.Int implements PrimStack {
        public Int(int length) {
            super();
        }

        public Int(int... arr) {
            super(arr);
        }
    }

    public static class Short extends Expand.Short implements PrimStack {
        public Short(int length) {
            super();
        }

        public Short(short... arr) {
            super(arr);
        }
    }

    public static class Byte extends Expand.Byte implements PrimStack {
        public Byte(int length) {
            super();
        }

        public Byte(byte... arr) {
            super(arr);
        }
    }
}
