// package old.heap;

// public abstract class PrimHeap<P extends PrimArray<ArrType>, ArrType> extends ExpandableArray.Prim<P, ArrType>
//         implements PushPop.Prim, HeapADT {

//     private PrimHeap(boolean max, P primArr) {
//         super(primArr);
//         this.max = max;
//     }

//     private PrimHeap(boolean max, P primArr, int size) {
//         super(primArr, size);
//         this.max = max;
//     }

//     private boolean max;

//     public boolean max() {
//         return max;
//     }

//     @Override
//     public boolean push(int i) {
//         if (!expand())
//             return false;
//         heapUp(size++, i, true);
//         return true;
//     }

//     @Override
//     public int primPop() {
//         int ret = peek();
//         heapDown(0, arr.get(--size), true);
//         return ret;
//     }

//     @Override
//     public int primPeek() {
//         return arr.get(0);
//     }

//     @Override
//     public boolean heapUp(int index) {
//         return heapUp(index, arr.get(index), false);
//     }

//     @Override
//     public boolean heapDown(int index) {
//         return heapDown(index, arr.get(index), false);
//     }

//     private boolean heapUp(int start, int value, boolean writeOnStart) {
//         int i = start;
//         while (i > 0) {
//             int up = up(i);
//             int top = arr.get(up);
//             if (compare(value, top))
//                 i = helpHeap(i, up, top);
//             else
//                 break;
//         }
//         return write(start, value, writeOnStart, i);
//     }

//     private boolean heapDown(int start, int value, boolean writeOnStart) {
//         int i = start;
//         int bottom = bottom();
//         while (i <= bottom) {
//             int l = left(i);
//             int left = arr.get(l);
//             int r = l + 1;
//             if (r >= size()) {
//                 if (compare(left, value))
//                     i = helpHeap(i, l, left);
//                 else
//                     break;
//             }
//             int right = arr.get(r);
//             if (compare(left, value) && !compare(right, left))
//                 i = helpHeap(i, l, left);
//             else if (compare(right, value))
//                 i = helpHeap(i, r, right);
//             else
//                 break;
//         }
//         return write(start, value, writeOnStart, i);
//     }

//     private int helpHeap(int index, int next, int value) {
//         arr.set(index, value);
//         return next;
//     }

//     private boolean write(int start, int value, boolean writeOnStart, int i) {
//         if (i == start && !writeOnStart)
//             return false;
//         arr.set(i, value);
//         return true;
//     }

//     private boolean compare(int a, int b) {
//         return max() ? a < b : a > b;
//     }

//     public static class Int extends PrimHeap<PrimArray.Int, int[]> {

//         public Int(boolean max, int length) {
//             super(max, new PrimArray.Int(length), 0);
//         }

//         public Int(boolean max, int... array) {
//             super(max, new PrimArray.Int(array));
//         }
//     }

//     public static class Short extends PrimHeap<PrimArray.Short, short[]> {

//         public Short(boolean max, int length) {
//             super(max, new PrimArray.Short(length), 0);
//         }

//         public Short(boolean max, short... array) {
//             super(max, new PrimArray.Short(array));
//         }
//     }

//     public static class Byte extends PrimHeap<PrimArray.Byte, byte[]> {

//         public Byte(boolean max, int length) {
//             super(max, new PrimArray.Byte(length), 0);
//         }

//         public Byte(boolean max, byte... array) {
//             super(max, new PrimArray.Byte(array));
//         }
//     }
// }
