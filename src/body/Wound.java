package body;

import ui.Viewable;

public class Wound implements Comparable<Wound>, Viewable {

	private String source;
	private int nonLethal;
	private int lethal;
	private int maim;

	private int clot;
	private float treat;
	private boolean exposed;

	public Wound(String src, int nl, int l, int m, boolean exposed) {
		source = src;
		nonLethal = nl;
		lethal = l;
		maim = m;
		this.exposed = exposed;
	}

	public void setDamage(Integer nl, Integer l, Integer m) {
		if (nl != null && nl >= 0)
			nonLethal = nl;
		if (l != null && l >= 0)
			lethal = l;
		if (m != null && m >= 0)
			maim = m;
	}

	public void modDamage(int nl, int l, int m) {
		nonLethal += nl;
		lethal += l;
		maim += m;
		if (nonLethal < 0)
			nonLethal = 0;
		if (lethal < 0)
			lethal = 0;
		if (maim < 0)
			maim = 0;
	}

	public String source() {
		return source;
	}

	/**
	 * @return damage that doesn't bleed and heals quickly
	 */
	public int nonLethal() {
		return nonLethal;
	}

	/**
	 * @return damage that bleeds and heals slowly
	 */
	public int lethal() {
		return lethal;
	}

	/**
	 * @return damage that bleeds and doesn't heal by normal means
	 */
	public int maim() {
		return maim;
	}

	/**
	 * @return the total damage
	 */
	public int getDamage() {
		return nonLethal + lethal + maim;
	}

	/**
	 * @return the severity of bleeding
	 */
	public int getBleed() {
		if (clot < 0)
			clot = 0;
		int bleed = lethal + maim - clot;
		if (bleed < 0) {
			clot = lethal + maim;
			return 0;
		}
		float mod = 1 - treat;
		if (!exposed)
			mod /= 2;
		return (int) (bleed * mod);
	}

	public float getTreat() {
		return treat;
	}

	public boolean exposed() {
		return exposed;
	}

	public void expose() {
		exposed = true;
	}

	/**
	 * slows bleeding
	 * 
	 * @param clot ammount to slow bleeding by
	 * @return if bleeding has stopped
	 */
	public boolean clot(int clot) {
		this.clot += clot;
		if (getBleed() <= 0)
			return true;
		return false;
	}

	/**
	 * opens the wound
	 * 
	 * @return if bleeding has started again
	 */
	public boolean open(float treatMod) {
		if (treatMod <= 0) {
			treat = 0;
		} else if (treatMod <= 1) {
			treat *= treatMod;
		}
		if (clot > maim) {
			clot = maim;
			return lethal > 0;
		}
		return false;
	}

	public void treat(float treatment) {
		if (treatment >= 1) {
			treat = 1;
		} else if (treatment <= 0) {
			treat = 0;
		} else {
			treat = treatment;
		}
	}

	public float infectRisk() {
		if (!exposed)
			return 0;
		return (float) Math.pow(.5 - treat / 2, 2);
	}

	public int infectDmg() {
		if (clot >= maim)
			return lethal;
		return lethal + maim - clot;
	}

	public boolean isScar() {
		return nonLethal == 0 && lethal == 0 && clot >= maim;
	}

//	// may need better name
//	public boolean isFresh() {
//		return clot < maim;
//	}

	private String addIcon(String str) {
		int bleed = getBleed();
		if (bleed > 0) {
			str += " *" + bleed;
			if (exposed)
				str += "^";
		} else if (exposed)
			str += " ^";
		if (treat > 0)
			str += " x" + (int) (treat * 100);
		return str;
	}

	@Override
	public String toString() {
		return addIcon(source + ": " + nonLethal + '|'//
				+ lethal + '|' + maim);
	}

	@Override
	public String lineString(boolean full) {
		if (full)
			return toString();
		return source + ": " + getDamage();
	}

	@Override
	public int compareTo(Wound other) {
		return getDamage() - other.getDamage();
	}

}
