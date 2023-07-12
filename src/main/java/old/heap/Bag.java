package old.heap;

import java.util.function.IntFunction;

import old.util.Math;

public class Bag<E> extends ExpandableArray.Array<E> implements PushPop<E> {

    public Bag(IntFunction<E[]> newArr, int length) {
        super(newArr, length);
    }

    @SafeVarargs
    public Bag(E... arr) {
        super(arr);
    }

    @Override
    public boolean push(E e) {
        if (e == null || !expand())
            return false;
        arr[size++] = e;
        return true;
    }

    private static int randInt(int size) {
        return Math.random.nextInt(size);
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        int rand = randInt(size);
        E ret = arr[rand];
        arr[rand] = arr[--size];
        arr[size] = null;
        return ret;
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return arr[randInt(size)];
    }

    // private static abstract class Prim<P extends PrimArray<ArrType>, ArrType>
    // extends ExpandableArray.Prim<P, ArrType>
    // implements PushPop.Prim {

    // private Prim(P primArr) {
    // super(primArr);
    // }

    // private Prim(P primArr, int size) {
    // super(primArr, size);
    // }

    // @Override
    // public boolean push(int i) {
    // if (!expand())
    // return false;
    // arr.set(size++, i);
    // return true;
    // }

    // @Override
    // public int primPop() {
    // int rand = randInt(size);
    // int ret = arr.get(rand);
    // arr.move(size--, rand);
    // return ret;
    // }

    // @Override
    // public int primPeek() {
    // return arr.get(randInt(size));
    // }
    // }

    // public static class Int extends Prim<PrimArray.Int, int[]> {

    // public Int(int length) {
    // super(new PrimArray.Int(length), 0);
    // }

    // public Int(int... array) {
    // super(new PrimArray.Int(array));
    // }

    // }

    // public static class Short extends Prim<PrimArray.Short, short[]> {

    // public Short(int length) {
    // super(new PrimArray.Short(length), 0);
    // }

    // public Short(short... array) {
    // super(new PrimArray.Short(array));
    // }

    // }

    // public static class Byte extends Prim<PrimArray.Byte, byte[]> {

    // public Byte(int length) {
    // super(new PrimArray.Byte(length), 0);
    // }

    // public Byte(byte... array) {
    // super(new PrimArray.Byte(array));
    // }

    // }
}
