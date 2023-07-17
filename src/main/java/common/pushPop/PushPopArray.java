package common.pushPop;

import java.util.Collection;
import java.util.Iterator;

/**
 * A PushPop backed by an ArrayWrapper.
 * 
 * @param G the type returned by get methods
 * @param S the type consumed by set methods
 * @param A the type of the wrapped array
 * @version 7/15/23
 */
public abstract class PushPopArray<G extends S, S, A> implements PushPop<G, S> {

    protected final ArrayWrapper<G, S, A> array;
    protected int size;

    protected PushPopArray(ArrayWrapper<G, S, A> array) {
        this.array = array;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return array.capacity();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return size() == capacity();
    }

    @Override
    public boolean push(S value) {
        if (value == null || isFull())
            return false;
        insert(value);
        return true;
    }

    protected void insert(S value) {
        array.set(size++, value);
    }

    /**
     * Attempts to push a Collection of values.
     * 
     * @return False if there is not enough room, or any of the values are null.
     */
    public boolean pushAll(Collection<S> values) {
        if (values.size() > capacity() - size() || values.stream().anyMatch(v -> v == null))
            return false;
        values.forEach(this::push);
        return true;
    }

    @Override
    public G peek() {
        return isEmpty() ? null : array.get(peekIndex());
    }

    protected abstract int peekIndex();

    @Override
    public G pop() {
        return isEmpty() ? null : remove();
    }

    protected abstract G remove();

    @Override
    public G swap(S value) {
        if (value == null)
            return pop();
        return isEmpty() ? array.cast(value) : swapHelp(value);
    }

    protected abstract G swapHelp(S value);

    @Override
    public Iterator<G> iterator() {
        return array.iterator(size);
    }

    /**
     * @return A String representation of data not stored in the array
     */
    public String header() {
        return getClass().getSimpleName() + ": " + size() + " / " + capacity();
    }

    /**
     * @return a String of raw values stored in the array
     */
    public String arrayString() {
        return array.arrayString();
    }

    @Override
    public String toString() {
        return header() + "\n" + values();
    }

}
