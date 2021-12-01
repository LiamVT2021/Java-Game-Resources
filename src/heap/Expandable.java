package heap;

public interface Expandable<ArrType> {

    public int size();

    public int capacity();

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

    public default boolean isEmpty() {
        return size() <= 0;
    }

    public default boolean isFull() {
        return size() >= capacity() && capacity() >= 0;
    }

    public default boolean isOver() {
        return size() > capacity() && capacity() >= 0;
    }

    public default boolean hasRoom() {
        return size() < capacity() || capacity() < 0;
    }

    public default boolean hasRoom(int ex) {
        return size() + ex <= capacity() || capacity() < 0;
    }

    public boolean trim();

    public ArrType array();

}
