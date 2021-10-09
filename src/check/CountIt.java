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
 * @param <R> The type of return for the count (boolean or int)
 */
public abstract class CountIt<P, R> implements IndexIt<R> {

	protected IndexIt<P> it;

	public CountIt(P[] predArr) {
		this(new Array<P>(predArr).iterator());
	}

	public CountIt(IndexIt<P> predIt) {
		it = predIt;
	}

	public void skip() {
		it.next();
	}

	public int remaining() {
		int rem = 0;
		while (hasNext()) {
			skip();
			rem++;
		}
		return rem;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public int index() {
		return it.index();
	}

//	@Override
//	public int prevIndex() {
//		return it.prevIndex();
//	}

//	@Override
//	public int max() {
//		return it.max();
//	}
//
//	@Override
//	public int remaining() {
//		return it.remaining();
//	}
}
