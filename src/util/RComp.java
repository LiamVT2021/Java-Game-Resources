package util;

import java.util.Comparator;

/**
 * Simple class to reverse ordering of a Comparator
 * 
 * @author Liam
 * @date 7/1/2021
 *
 * @param <E> type of Object we are comparing
 */
public class RComp<E> implements Comparator<E> {

	private Comparator<? super E> comp;

	public RComp(Comparator<? super E> comp) {
		this.comp = comp;
	}

	@Override
	public int compare(E o1, E o2) {
		return comp.compare(o2, o1);
	}

}
