package util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

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

	public default boolean has(E target) {
		return get(target) != null;
	}

	public default E get(E target) {
		if (target == null)
			return null;
		for (E cur : this) {
			if (target.equals(cur))
				return cur;
		}
		return null;
	}

	public default E getMax(Comparator<? super E> comp) {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			return null;
		E ret = it.next();
		while (it.hasNext()) {
			E cur = it.next();
			if (comp.compare(cur, ret) > 0)
				ret = cur;
		}
		return ret;
	}

	public default E[] getMax(Comparator<? super E> comp, E[] arr) {
		Iterator<E> it = iterator();
		int count = 0;
		int l = arr.length;
		PriorityQueue<E> heap = new PriorityQueue<E>(l, comp);
		while (it.hasNext() && count < l) {
			heap.add(it.next());
			count++;
		}
		while (it.hasNext()) {
			E cur = it.next();
			if (comp.compare(cur, heap.peek()) > 0) {
				heap.poll();
				heap.add(cur);
			}
		}
		for (int i = l - 1; i >= count; i--)
			arr[i] = null;
		for (int i = count - 1; i >= 0; i--)
			arr[i] = heap.poll();
		return arr;
	}

	public default E getMin(Comparator<? super E> comp) {
		return getMax(new RComp<E>(comp));
	}

	public default E[] getMin(Comparator<? super E> comp, E[] arr) {
		return getMax(new RComp<E>(comp), arr);
	}

}
