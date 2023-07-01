// package old.util;

// import java.util.Iterator;

// public class RangeArray<E> implements IterableExt<E> {

//     private Array<E> arr;
//     private int start;
//     private int cut;

//     public RangeArray(Array<E> arr, int start, int cut) {
//         this.arr = arr;
//         this.start = start;
//         this.cut = cut;
//     }

//     @Override
//     public Iterator<E> iterator() {
//         return arr.iterator(start, cut);
//     }

//     public int start() {
//         return start;
//     }

//     public int cut() {
//         return cut;
//     }

// }