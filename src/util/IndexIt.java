package util;

import java.util.Iterator;

/**
 * Add the ability to check the index of the next object (or count of prev)
 * 
 * @author Liam
 * @version 7/22/21
 *
 * @param <E> the class of the we are iterating
 */
public interface IndexIt<E> extends Iterator<E> {

	public int index();

//	public int prevIndex();

//	public int max();

//	public int remaining();

}
