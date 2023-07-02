package common.pushPop;

import java.util.Iterator;
import java.util.Random;

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

    @Override
    public G peek() {
        return array.get(draw());
    }

    @Override
    public G pop() {
        int i = draw();
        G ret = array.get(i);
        array.set(i, array.remove(--size));
        return ret;
    }

    @Override
    public G swap(S value) {
        return array.swap(draw(), value);
    }

    @Override
    public Iterator<G> iterator() {
        return array.iterator(size);
    }

    public static class Gen<V> extends Bag<V, V, V[]> {

        public Gen(V[] array) {
            super(new GenericArray<>(array));
        }

    }
}
