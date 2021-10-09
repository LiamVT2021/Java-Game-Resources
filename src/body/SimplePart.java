package body;

import java.util.Collection;

public class SimplePart extends BodyPart {

	public SimplePart(String group, String name, int HP) {
		super(group, name, HP);
	}

	@Override
	public Iterable<Wound> allWounds() {
		return this;
	}

	@Override
	public boolean hasParts() {
		return false;
	}

	@Override
	public Collection<BodyPart> allParts() {
		return null;
	}

	@Override
	public Iterable<BodyPart> destroyParts() {
		return null;
	}

}
