// package old.heap;

// import java.util.ArrayDeque;
// import java.util.Comparator;
// import java.util.Queue;
// import java.util.function.IntFunction;
// import java.util.function.Supplier;

// public class MiddleQueue<E> implements PushPop<E> {

//     private Heap<E> top;
//     private Heap<E> bottom;
//     private Queue<E> queue;
//     private Supplier<E> supply;

//     public MiddleQueue(Comparator<E> comp, IntFunction<E[]> newArr, int bottomSize, int queueSize, int topSize) {
//         top = new Heap<E>(newArr, topSize, Heap.min(comp)).lock();
//         bottom = new Heap<E>(newArr, topSize, Heap.max(comp)).lock();
//         queue = new ArrayDeque<E>(queueSize);
//     }

//     @Override
//     public boolean push(E e) {
//         if (top.push(e))
//             return true;
//         if (bottom.push(top.swap(e)))
//             return true;
//         if (queue.add(bottom.swap(e)))
//             return true;
//         return false;
//     }

//     @Override
//     public E pop() {
//         if (!queue.isEmpty())
//             return queue.poll();
//         if (supply == null)
//             return null;
//         push(supply.get());
//         return queue.poll();
//     }

//     @Override
//     public E peek() {
//         if (!queue.isEmpty())
//             return queue.peek();
//         if (supply == null)
//             return null;
//         push(supply.get());
//         return queue.peek();
//     }

//     @Override
//     public boolean isEmpty() {
//         return queue.isEmpty() && supply == null;
//     }

//     @Override
//     public int size() {
//         return queue.size();
//     }

// }