package old.ui;

/**
 * objects that can be viewed as a one line string
 * 
 * @param Back class that stores data relevant for display
 * @author Liam
 * @version 6/20/2021
 */
public interface Viewable<Back> {

	public String lineString(Back back);

	public default String lineString() {
		return lineString(null);
	}

	/**
	 * optional
	 * 
	 * @return an abbreviated string
	 */
	public default String str(Back back) {
		return lineString(back);
	}

	public default String str() {
		return str(null);
	}
}
