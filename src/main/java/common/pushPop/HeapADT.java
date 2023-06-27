package common.pushPop;

public abstract class HeapADT<V> implements PushPop<V> {

    private int size;

    protected abstract boolean belongsAbove(V a, V b);

    public abstract V get(int index);

    protected abstract void set(int index, V value);

    public int size() {
        return size;
    }

    private int up(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    @Override
    public boolean push(V value) {
        if (value == null || isFull())
            return false;
        int b = size++;
        int t = up(b);
        V cur = get(t);
        while (belongsAbove(value, cur)) {
            set(b, cur);
            if (t == 0) {
                set(0, value);
                return true;
            }
            b = t;
            t = up(b);
            cur = get(t);
        }
        set(b, value);
        return true;
    }

    @Override
    public V peek() {
        return isEmpty() ? null : get(0);
    }

    @Override
    public V pop() {
        if (isEmpty())
            return null;
        V value = get(--size);
        set(size, null);
        return heapDown(value);
    }

    @Override
    public V swap(V value) {
        if (isEmpty())
            return value;
        return heapDown(value);
    }

    private V heapDown(V value) {
        V ret = get(0);
        int t = 0;
        while (true) {
            int l = leftChild(t);
            if (l < size) {
                V left = get(l);
                int r = l + 1;
                if (r < size) {
                    V right = get(r);
                    if (belongsAbove(right, value)) {
                        if (belongsAbove(left, right)) {
                            set(t, left);
                            t = l;
                        } else {
                            set(t, right);
                            t = r;
                        }
                        continue;
                    }
                }
                if (belongsAbove(left, value)) {
                    set(t, left);
                    t = l;
                    continue;
                }
            }
            set(t, value);
            return ret;
        }
    }

}
