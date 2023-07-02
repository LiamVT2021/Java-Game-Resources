package common.pushPop;

import java.util.Iterator;
import java.util.Random;

/**
 * A PushPop data structure that removes elements by random
 * 
 * @param G the type returned by get methods
 * @param S the type consumed by set methods
 * @param A the type of the wrapped array
 * @version 7/2/23
 */
public abstract class Bag<G extends S, S, A> extends PushPop.Array<G, S, A> {

    private static final Random rand = new Random();

    protected Bag(ArrayWrapper<G, S, A> array) {
        super(array);
    }

    @Override
    public boolean push(S value) {
        if (value == null || isFull())
            return false;
        array.set(size++, value);
        return true;
    }

    private int draw() {
        return rand.nextInt(size);
    }

    /**
     * @return a random element from the bag, null if empty
     */
    @Override
    public G peek() {
        return isEmpty() ? null : array.get(draw());
    }

    /**
     * removes a random element from the bag
     */
    @Override
    public G pop() {
        if (isEmpty())
            return null;
        int i = draw();
        G ret = array.get(i);
        array.set(i, array.remove(--size));
        return ret;
    }

    @Override
    public G swap(S value) {
        if (value == null)
            return null;
        return isEmpty() ? null : array.swap(draw(), value);
    }

    @Override
    public Iterator<G> iterator() {
        return array.iterator(size);
    }

    /**
     * A Bag containing Objects removed by random draw
     * 
     * @param V the type of the objects in the bag
     */
    public static class Gen<V> extends Bag<V, V, V[]> {

        public Gen(V[] array) {
            super(new GenericArray<>(array));
        }

    }
}
