package test;

import ui.Viewable;

public interface Wound extends Comparable<Wound>, Viewable {

	// Strings

	public String description();

	public String source();

	// Damage Types

	/**
	 * @return damage that doesn't bleed and heals quickly
	 */
	public int nonLethal();

	/**
	 * @return damage that bleeds and heals slowly
	 */
	public int lethal();

	/**
	 * @return damage that bleeds and wont heal unless treated
	 */
	public int maim();

	/**
	 * @return damage that doesn't bleed and wont heal by normal means
	 */
	public int scar();

	/**
	 * @return the total damage
	 */
	public int getDamage();

	// Scar Handleing

	public boolean isScar();

	public Scar makeScar();

	// Treatment

	public void treat(float treat);

	public float getTreat();

	public boolean clot(int clot);

	public int getBleed();

	public boolean open(float treatMod);

	@Override
	public default int compareTo(Wound other) {
//		return getDamage() - other.getDamage();
		if (isScar()) {
			if (other.isScar())
				return getDamage() - other.getDamage();
			else
				return -1;
		} else {
			if (other.isScar())
				return 1;
			else
				return getDamage() - other.getDamage();
		}
	}

}
