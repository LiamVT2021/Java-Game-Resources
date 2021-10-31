package heap;

public interface PushPop<E> {

    public boolean push(E e);

    public E pop();

    public E peek();

    public default E swap(E e) {
        if (push(e))
            return pop();
        E ret = pop();
        push(e);
        return ret;
    }
}
