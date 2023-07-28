package old.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;

// import heap.Heap;

/**
 * extends the Iterable interface with some default helper methods
 * 
 * @author Liam
 * @date 7/2/2021
 *
 * @param <E> type of Object in Iterable
 */
@FunctionalInterface
public interface IterableExt<E> extends Iterable<E> {

    public default E getMax(Comparator<? super E> comp) {
        return reduce((a, b) -> comp.compare(a, b) > 0 ? a : b);
    }

    // public default E[] getMax(Comparator<? super E> comp, IntFunction<E[]>
    // newArr, int size) {
    // return reduce(Heap.min(comp), newArr, size).sorted(newArr, false, true);
    // }

    public default E getMin(Comparator<? super E> comp) {
        return reduce((a, b) -> comp.compare(a, b) < 0 ? a : b);
    }

    // public default E[] getMin(Comparator<? super E> comp, IntFunction<E[]>
    // newArr, int size) {
    // return reduce(Heap.max(comp), newArr, size).sorted(newArr, false, true);
    // }

    public default E reduce(BinaryOperator<E> operator) {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return null;
        E ret = it.next();
        while (it.hasNext())
            ret = operator.apply(ret, it.next());
        return ret;
    }

    // public default Heap<E> reduce(BiPredicate<E, E> comp, IntFunction<E[]>
    // newArr, int size) {
    // Heap<E> heap = new Heap<E>(newArr, size, comp);
    // Iterator<E> it = iterator();
    // while (it.hasNext() && !heap.isFull())
    // heap.push(it.next());

    // while (it.hasNext()) {
    // heap.swap(it.next());
    // }
    // return heap;
    // }

}
