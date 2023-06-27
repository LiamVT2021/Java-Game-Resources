package old.util;

import java.util.Iterator;

/**
 * Add the ability to check the index of the previous object
 * 
 * @author Liam
 * @version 7/22/21
 *
 * @param <E> the class of the we are iterating
 */
public interface IndexIt<E> extends Iterator<E> {

	public int prevIndex();

}
