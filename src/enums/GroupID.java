package enums;

public class GroupID<E> {

    private E first;
    private int size;

    public void put(E e) {
        if (first == null)
            first = e;
        size++;
    }

    public E first() {
        return first;
    }

    public int size() {
        return size;
    }
}
