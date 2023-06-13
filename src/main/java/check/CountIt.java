package check;

import util.Array;
import util.IndexIt;

/**
 * Takes a IndexIt of predicates and converts to IndexIt of booleans
 * 
 * to be used for counting
 * 
 * @author Liam
 * @version 7/22/21
 *
 * @param <P> The type of Predicate (or BiPredicate)
 */
public abstract class CountIt<P> implements IndexIt<Boolean> {

	protected IndexIt<P> it;

	public CountIt(P[] predArr) {
		this(new Array<P>(predArr).iterator());
	}

	public CountIt(IndexIt<P> predIt) {
		it = predIt;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public int prevIndex() {
		return it.prevIndex();
	}
}
