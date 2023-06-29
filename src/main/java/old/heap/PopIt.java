// package old.heap;

// import java.util.Iterator;

// class PopIt<E> implements Iterator<E> {

//     private PushPop<E> pushPop;

//     public PopIt(PushPop<E> pushPop) {
//         this.pushPop = pushPop;
//     }

//     @Override
//     public boolean hasNext() {
//         return !pushPop.isEmpty();
//     }

//     @Override
//     public E next() {
//         return pushPop.pop();
//     }

//     public static class Prim implements Iterator<Integer> {
//         private PushPop.Prim pushPop;

//         public Prim(PushPop.Prim pushPop) {
//             this.pushPop = pushPop;
//         }

//         @Override
//         public boolean hasNext() {
//             return !pushPop.isEmpty();
//         }

//         @Override
//         public Integer next() {
//             return pushPop.pop();
//         }
//     }
// }
