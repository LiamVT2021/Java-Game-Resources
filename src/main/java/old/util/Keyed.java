package old.util;

/**
 * an object that can be idetified by a key
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <K> the class of the key
 */
public interface Keyed<K> {

	public K key();

}
