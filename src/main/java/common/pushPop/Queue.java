package common.pushPop;

public abstract class Queue<G extends S, S, A> extends PushPop.Array<G, S, A> {

    private int next;

    public Queue(ArrayWrapper<G, S, A> array) {
        super(array);
    }

    private G next() {
        G ret = array.remove(next);
        next = (next + 1) % array.capacity();
        return ret;
    }

    private void insert(S value) {
        array.set((next + size) % array.capacity(), value);
    }

    @Override
    public boolean push(S value) {
        if (isFull())
            return false;
        insert(value);
        size++;
        return true;
    }

    @Override
    public G peek() {
        return array.get(next);
    }

    @Override
    public G pop() {
        if (isEmpty())
            return null;
        size--;
        return next();
    }

    @Override
    public G swap(S value) {
        if (isEmpty())
            return array.cast(value);
        insert(value);
        return next();
    }

    public class Gen<V> extends Queue<V, V, V[]> {

        public Gen(V[] array) {
            super(new GenericArray<>(array));
        }

    }

}
