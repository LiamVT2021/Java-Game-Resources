package body;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ui.Page;
import ui.ViewCol;
import ui.Viewable;
import util.Array;
import util.MultiIt;
import util.Named;
import util.SingleIt;

public abstract class BodyPart
		implements Iterable<Wound>, ViewCol, Page, Viewable, Named {

	private String group;
	private String name;
	private int maxHP;
	private Collection<Wound> wounds;
	private Wound detach;
	private Wound destroy;

	public BodyPart(String groupName, String name, int HP) {
		group = groupName;
		this.name = name;
		maxHP = HP;
		wounds = new ArrayList<Wound>();
	}

	public String group() {
		return group;
	}

	
	
	public String name(boolean full) {
		return name;
	}

//	@Override
	public Iterable<Wound> wounds(boolean all) {
		if (all && hasParts())
			return new MultiIt<Wound>(allParts());
		return this;
	}

	@Override
	public Iterator<Wound> iterator() {
		if (isDetached())
			return new SingleIt<Wound>(detach);
		if (isDestroyed())
			return new SingleIt<Wound>(destroy);
		return wounds.iterator();
	}

	public int getDamage(boolean all) {
		int dmg = 0;
		for (Wound wound : wounds(all))
			dmg += wound.getDamage();
		return dmg;
	}

	public int[] getTotals(boolean all, boolean icons) {
		int[] arr;
		if (icons)
			arr = new int[5];
		else
			arr = new int[3];
		float treat = 0;
		for (Wound wound : wounds(all)) {
			arr[0] += wound.nonLethal();
			arr[1] += wound.lethal();
			arr[2] += wound.maim();
			if (icons) {
				arr[3] += wound.getBleed();
				if (!wound.isScar()) {
					arr[4]++;
					treat += wound.getTreat();
				}
			}
		}
		if (icons && arr[4] != 0)
			arr[4] = (int) (100 * treat / arr[4]);
		return arr;
	}

	public float efficiency() {
		return health() * 100 / (float) maxHP;
	}

	public int health() {
		if (isDetached())
			return 0;
		return maxHP - getDamage(false);
	}

	public int maxHealth() {
		if (isDetached())
			return 0;
		return maxHP;
	}

	public Wound wound(String source, int nonLethal, int lethal,
			int maim, boolean exposed) {
		int[] arr = getTotals(false, false);
		if (isDetached())
			return null;
		int temp = nonLethal + arr[0] - maxHP;
		if (temp > 0) {
			nonLethal -= temp;
			lethal += temp;
		}
		temp = lethal + arr[1] - maxHP;
		if (temp > 0) {
			lethal -= temp;
			maim += temp;
		}
		Wound wound = new Wound(source, nonLethal, lethal, maim,
				exposed);
		wounds.add(wound);
		if (arr[1] + arr[2] >= 2 * maxHP)
			return destroy(source);
		return wound;
	}

	public Wound destroy(String source) {
		destroyed = true;
		return detach(source);
	}

	public Wound detach(String source) {
		detach = new Wound(source, 0, maxHP, maxHP, true);
		return detach;
	}

	public void attach() {
		detach = null;
		destroyed = false;
	}

	public boolean isDestroyed() {
		return destroy != null;
	}

	public boolean isDetached() {
		return detach != null;
	}

	public boolean isMissing() {
		return isDetached() || isDestroyed();
	}

	@Override
	public String toString() {
		return page(true, true);
	}

	@Override
	public String page(boolean full) {
		return page(full, false);
	}

	@Override
	public String lineString(boolean full) {
		if (!full)
			return addIcon(name + " - " + health() + " / " + maxHP);
		return addIcon(name + " - " + nonLethal() + '|' + lethal()
				+ '|' + maim()) + " / " + maxHP;
	}

	private String page(boolean full, boolean forceOut) {
		if (destroyed)
			return addIcon(name + " - Destroyed");
		else if (isDetached())
			return null;
		return lineString(full) + "\n" + viewPage(full);
	}

	@Override
	public Iterable<? extends Viewable> viewCol() {
		return wounds;
	}

	public abstract boolean hasParts();

	public abstract Iterable<BodyPart> allParts();

	public abstract Iterable<BodyPart> destroyParts();

}
