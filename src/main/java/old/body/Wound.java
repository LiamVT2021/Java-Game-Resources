package old.body;

public class Wound {
	private String source;
	private int nonLethal;
	private int lethal;
	private int maim;
//	private int mod;

	public Wound(String src, int nl, int l, int m) {
		source = src;
		nonLethal = nl;
		lethal = l;
		maim = m;
	}

	public int getBleed() {
		return lethal + maim;
	}

	public int getDamage() {
		return nonLethal + lethal + maim;
	}

	public String toString() {
		return source + ": " + nonLethal + '|' + lethal + '|' + maim;
	}

}
