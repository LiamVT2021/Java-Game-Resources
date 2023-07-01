// package old.heap;

// public abstract class PrimQueue<P extends PrimArray<ArrType>, ArrType> extends ExpandableArray.Prim<P, ArrType>
//         implements PushPop.Prim {

//     private PrimQueue(P primArr) {
//         super(primArr);
//     }

//     private PrimQueue(P primArr, int size) {
//         super(primArr, size);
//     }

//     protected int index;

//     protected int end(int s) {
//         return (index + s) % arr.length();
//     }

//     @Override
//     public boolean push(int i) {
//         if (!expand())
//             return false;
//         arr.set(end(size++), i);
//         return true;
//     }

//     @Override
//     public int primPop() {
//         return arr.get(end(--size));
//     }

//     @Override
//     public int primPeek() {
//         return arr.get(end(size - 1));
//     }

//     public static class Int extends PrimQueue<PrimArray.Int, int[]> {

//         public Int(int length) {
//             super(new PrimArray.Int(length), 0);
//         }

//         public Int(int... array) {
//             super(new PrimArray.Int(array));
//         }
//     }

//     public static class Short extends PrimQueue<PrimArray.Short, short[]> {

//         public Short(int length) {
//             super(new PrimArray.Short(length), 0);
//         }

//         public Short(short... array) {
//             super(new PrimArray.Short(array));
//         }
//     }

//     public static class Byte extends PrimQueue<PrimArray.Byte, byte[]> {

//         public Byte(int length) {
//             super(new PrimArray.Byte(length), 0);
//         }

//         public Byte(byte... array) {
//             super(new PrimArray.Byte(array));
//         }
//     }

// }
