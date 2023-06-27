package old.util;

import java.util.HashMap;

/**
 * Adds helper methods to Maps storing Keyed Objects
 * 
 * @author Liam
 * @version 7/20/21
 *
 * @param <K> the class of the keys
 * @param <V> the class of the objects
 */
public class KeyedMap<K, V extends Keyed<K>> extends HashMap<K, V> {

	/**
	 * dont understand this yet
	 */
	private static final long serialVersionUID = 4912719807314160018L;

	public KeyedMap() {
		super();
	}

	public KeyedMap(int initialCapacity) {
		super(initialCapacity);
	}

	public KeyedMap(int initialCapacity, int loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public KeyedMap(Iterable<V> keyedSet) {
		this();
		putAll(keyedSet);
	}

	public V put(V val) {
		return put(val.key(), val);
	}

	public void putAll(Iterable<V> vals) {
		for (V val : vals)
			put(val);
	}

	public V putIfAbsent(V val) {
		return putIfAbsent(val.key(), val);
	}
}
