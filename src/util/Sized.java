package util;

public interface Sized extends Container {

    public int size();

    public default int capacity() {
        return -1;
    }

    @Override
    public default boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public default boolean isFull() {
        return size() >= capacity() && capacity() >= 0;
    }

    @Override
    public default boolean isOver() {
        return size() > capacity() && capacity() >= 0;
    }

    @Override
    public default boolean hasRoom() {
        return size() < capacity() || capacity() < 0;
    }

    @Override
    public default boolean hasRoom(int ex) {
        return size() + ex <= capacity() || capacity() < 0;
    }

}
