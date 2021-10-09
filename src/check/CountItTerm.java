package check;

import util.Array;
import util.IndexIt;

public abstract class CountItTerm<P> extends CountIt<P, Integer> {

	public CountItTerm(P[] predArr) {
		super(new Array<P>(predArr).iterator());
	}

	public CountItTerm(IndexIt<P> predIt) {
		super(predIt);
	}

	public abstract int count(int goal);

	public abstract int termCount(int goal);

	public abstract void pf(PassFail pf);

}
