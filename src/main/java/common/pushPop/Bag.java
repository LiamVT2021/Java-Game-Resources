package common.pushPop;

import java.util.Random;

/**
 * A PushPop data structure that removes elements by random.
 * 
 * @param G the type returned by get methods
 * @param S the type consumed by set methods
 * @param A the type of the wrapped array
 * @version 7/15/23
 */
public abstract class Bag<G extends S, S, A> extends PushPop.Array<G, S, A> {

    private static final Random rand = new Random();

    protected Bag(ArrayWrapper<G, S, A> array) {
        super(array);
    }

    @Override
    protected int peekIndex() {
        return rand.nextInt(size);
    }

    /**
     * Removes a random element from the Bag.
     */
    @Override
    public G remove() {
        int i = peekIndex();
        G ret = array.get(i);
        array.set(i, array.remove(--size));
        return ret;
    }

    @Override
    public G swap(S value) {
        if (value == null)
            return pop();
        return isEmpty() ? array.cast(value) : array.swap(peekIndex(), value);
    }

    public static class GenBag<V> extends Bag<V, V, V[]> {

        public GenBag(V[] array) {
            super(new GenericArray<>(array));
        }

    }
}
