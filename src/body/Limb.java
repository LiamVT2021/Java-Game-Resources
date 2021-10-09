package body;

import java.util.ArrayList;
import java.util.Collection;

public class Limb extends BodyPart {

	private SimplePart tip;

	public Limb(String group, String name, int HP, String tipName,
			int tipHP) {
		super(group, name, HP);
		tip = new SimplePart(group, tipName, tipHP);
	}

	@Override
	public boolean hasParts() {
		return true;
	}

	@Override
	public Iterable<BodyPart> allParts() {
		Collection<BodyPart> list = new ArrayList<BodyPart>(2);
		list.add(this);
		list.add(tip);
		return list;
	}

	@Override
	public Iterable<BodyPart> destroyParts() {
		Collection<BodyPart> list = new ArrayList<BodyPart>(1);
		list.add(tip);
		tip = null;
		return list;
	}

}
