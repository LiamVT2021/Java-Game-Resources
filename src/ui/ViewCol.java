package ui;

import java.util.Iterator;

//import com.sun.tools.javac.util.StringUtils;

/**
 * a Collection of Viewable objects
 * 
 * @author Liam
 *
 */
@FunctionalInterface
public interface ViewCol {

	public Iterable<? extends Viewable> viewCol();

	public default String viewPage(boolean full) {
		Iterator<? extends Viewable> it = viewCol().iterator();
		if (!it.hasNext())
			return null;
		String page = it.next().lineString(full);
		while (it.hasNext()) {
			page += it.next().lineString(full) + '\n';
		}
		return page;
	}

}
