package test;

public class Scar implements Wound {

	private String description;
	private String source;
	private int scar;

	public Scar(String description, String source, int damage) {
		this.description = description;
		this.source = source;
		scar = damage;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public String source() {
		return source;
	}

	@Override
	public int nonLethal() {
		return 0;
	}

	@Override
	public int lethal() {
		return 0;
	}

	@Override
	public int maim() {
		return 0;
	}

	@Override
	public int scar() {
		return scar;
	}

	@Override
	public int getDamage() {
		return scar;
	}

	@Override
	public boolean isScar() {
		return true;
	}

	@Override
	public Scar makeScar() {
		return null;
	}

	@Override
	public void treat(float treat) {
		return;
	}

	@Override
	public float getTreat() {
		return 0;
	}

	@Override
	public boolean clot(int clot) {
		return false;
	}

	@Override
	public int getBleed() {
		return 0;
	}

	@Override
	public boolean open(float treatMod) {
		return false;
	}

	@Override
	public String toString() {
		return description + " Scar (" + source + "): " + scar;
	}

	@Override
	public String lineString(boolean full) {
		if (full)
			return toString();
		return description + " Scar: " + scar;
	}
}
