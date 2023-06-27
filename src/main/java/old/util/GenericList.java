package old.util;

import java.util.ArrayList;
import java.util.List;

public class GenericList<E> {

    private final List<E> list;
    private int next;

    public GenericList() {
        list = new ArrayList<>();
        next = 1;
    }

    public int next() {
        return next;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void add(E e) {
        list.add(e);
        next++;
    }

    public void remove(E e) {
        list.remove(e);
    }

}
