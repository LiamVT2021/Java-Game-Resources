package heap;

public interface PrimPushPop {

    public boolean push(int i);

    public int pop();

    public int peek();

    public default int swap(int i) {
        if (push(i))
            return pop();
        int ret = pop();
        push(i);
        return ret;
    }

}
