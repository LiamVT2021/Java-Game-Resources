package heap;

public class PrimQueue extends Expand.Int implements PushPop.Prim {

    private int index;


    public PrimQueue(int length) {
        super(length);
    }

    public PrimQueue(int... arr) {
        super(arr);
    }

    public boolean arrCopy(int newSize) {
        if (newSize == arr.length)
            return false;
        int[] newArr = new int[newSize];
        int l = Math.min(newSize, size);
        for (int i = 0; i < l; i++)
            newArr[i] = arr[(i + index) % arr.length];
        arr = newArr;
        index = 0;
        return true;
    }

    @Override
    public boolean push(int i) {
        if (isFull())
            arr[(index + size++) % arr.length] = i;
        return true;
    }

    @Override
    public int primPop() {
        size--;
        return arr[index++ % arr.length];
    }

    @Override
    public int primPeek() {
        return arr[index];
    }

    public int last() {
        return arr[(index + size) % arr.length];
    }

}
