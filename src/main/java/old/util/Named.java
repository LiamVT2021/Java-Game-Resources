package old.util;

/**
 * object that can be identified by a name in the form of a string
 * 
 * @author Liam
 * @version 7/20/21
 */
public interface Named extends Keyed<String> {

	public String name(boolean full);

	public default String key() {
		return name(false);
	}

}
