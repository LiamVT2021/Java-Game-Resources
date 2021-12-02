package heap;

import util.Sized;

public interface Expandable<ArrType> extends Sized {

    public int length();

    public boolean setLength(int length);

    public Expandable<ArrType> setRange(int min, int capacity);

    public default Expandable<ArrType> setRange(int lock) {
        return setRange(lock, lock);
    }

    public default Expandable<ArrType> free() {
        return setRange(-1);
    }

    public default Expandable<ArrType> lock() {
        return setRange(length());
    }

    public boolean trim();

    public ArrType array();

    public static interface PushPop<E> extends Expandable<E[]>, heap.PushPop<E> {
        
    }

    public static interface Prim<ArrType> extends Expandable<ArrType>, heap.PushPop.Prim {

    }

}
