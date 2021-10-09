package check;

public class TermCheckSet<C> implements TermIntCheck<C> {

	private TermIntCheck<? super C>[] checks;
	private CountType type;

	public TermCheckSet(TermIntCheck<? super C>[] checkArr) {
		checks = checkArr;
	}

	@Override
	public int quickInt(C checkItem, Integer goal) {
		return type.intCount(new It(checkItem), goal);
	}

//	@Override
//	public int termInt(C checkItem, int goal) {
//		return type.termIntCount(new It(checkItem), goal,
//				max() - goal);
//	}

//	private int count(C checkItem, Integer goal, boolean quick) {
//		int total = 0;
//		if (goal == null)
//			for (TermIntCheck<? super C> check : checks)
//				total += check.quickInt(checkItem, goal);
//		else {
//			int index = 0;
//			int rem = goal;
//			while (total < goal && (quick || rem <= max(index))) {
//				TermIntCheck<? super C> check = checks[index];
//				total += check.quickInt(checkItem, rem);
//				rem = goal - total;
//				index++;
//			}
//		}
//		return total;
//	}

	@Override
	public int max() {
		int max = 0;
		for (TermIntCheck<? super C> check : checks)
			max += check.max();
		return max;
	}

	private int maxType() {
		if (type != CountType.MAX)
			return max();
		int max = 0;
		for (TermIntCheck<? super C> check : checks)
			if (check.max() > max)
				max = check.max();
		return max;
	}

//	private int max(int start) {
//		int max = 0;
//		for (int i = start; i < checks.length; i++) {
//			TermIntCheck<? super C> check = checks[i];
//			max += check.max();
//		}
//		return max;
//	}

	@Override
	public PassFail termPF(C checkItem, int goal, int maxFails) {
		int pass = type.termIntCount(new It(checkItem), goal,
				maxFails);
		return new PassFail(pass, maxType() - pass);
	}

	private class It extends CountItTerm<TermIntCheck<? super C>> {

		private C item;

		public It(C checkItem) {
			super(checks);
			item = checkItem;
		}

		@Override
		public Integer next() {
			return it.next().applyAsInt(item);
		}

		@Override
		public int count(int goal) {
			return it.next().quickInt(item, goal);
		}

		@Override
		public int termCount(int goal) {
			return it.next().termInt(item, goal);
		}

		@Override
		public void pf(PassFail pf) {
			pf.add(it.next().termPF(item, -pf.getPass(),
					-pf.getFail()));
		}
	}

}
