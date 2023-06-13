package util;

public interface Container {

    public boolean isEmpty();

    public default boolean isFull() {
        return false;
    }

    public default boolean isOver() {
        return false;
    }

    public default boolean hasRoom() {
        return true;
    }

    public default boolean hasRoom(int ex) {
        return true;
    }

}
