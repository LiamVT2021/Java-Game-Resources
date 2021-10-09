package ui;

/**
 * objects that can be viewed as a one line string
 * 
 * @author Liam
 * @version 6/20/2021
 */
@FunctionalInterface
public interface Viewable {

	public String lineString(boolean full);

//	public default String lineString() {
//		return lineString(null);
//	}

//	/**
//	 * optional
//	 * 
//	 * @return an abbreviated string
//	 */
//	public default String str() {
//		return lineString();
//	}

//	public default String str() {
//		return str(null);
//	}
}
