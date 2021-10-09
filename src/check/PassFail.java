package check;

public class PassFail {

	private int pass;
	private int fail;

	public PassFail() {
		this(0, 0);
	}

	public PassFail(int pass, int fail) {
		this.pass = pass;
		this.fail = fail;
	}

	public boolean test(boolean bool) {
		if (bool)
			pass();
		else
			fail();
		return bool;

	}

	public void pass() {
		pass++;
	}

	public void fail() {
		fail++;
	}

	public void add(PassFail pf) {
		pass += pf.pass;
		fail += pf.fail;
	}

	public void add(int pass, int fail) {
		this.pass += pass;
		this.fail += fail;
	}

	public int getPass() {
		return pass;
	}

	public int getFail() {
		return fail;
	}

	public boolean atGoal(int goal) {
		return pass >= goal;
	}

	public boolean hasFail(int maxFail) {
		return fail >= maxFail;
	}

}
