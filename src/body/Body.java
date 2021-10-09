package body;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ui.Book;
import ui.ViewCol;
import ui.Viewable;
import util.Array;
import util.MultiCol;

public class Body implements WoundCol, Book, ViewCol {

	private Map<String, BodyPart> bodyParts;
	private BodyPart[] coreParts;

	public Body() {
		bodyParts = new HashMap<String, BodyPart>();
//		bodyParts.put("Head", new BodyPart(60));
//		bodyParts.put("Upper Torso", new BodyPart(130));
//		bodyParts.put("Lower Torso", new BodyPart(120));
//		bodyParts.put("Left Arm", new BodyPart(70));
//		bodyParts.put("Right Arm", new BodyPart(70));
//		bodyParts.put("Left Hand", new BodyPart(20));
//		bodyParts.put("Right Hand", new BodyPart(20));
//		bodyParts.put("Left Leg", new BodyPart(80));
//		bodyParts.put("Right Leg", new BodyPart(80));
//		bodyParts.put("Left Foot", new BodyPart(20));
//		bodyParts.put("Right Foot", new BodyPart(20));
	}

	@Override
	public Iterable<Wound> wounds() {
		return new MultiCol<Wound>(bodyParts.values());
	}

	private void addPart(BodyPart part) {
		if (part == null)
			return;
		else if (part.hasParts()) {
			for (BodyPart p : part.allParts())
				addPart(p);
		} else
			bodyParts.put(part.name(), part);
	}

	public BodyPart getPart(String bodyPart) {
		return bodyParts.get(bodyPart);
	}

	public Wound wound(String bodyPart, String source, int nonLethal,
			int lethal, int maim, boolean exposed) {
		BodyPart part = getPart(bodyPart);
		if (part == null)
			return null;
		Wound wound = part.wound(source, nonLethal, lethal, maim,
				exposed);
		if (part.isDestroyed() && part.hasParts()) {
			for (BodyPart p : part.destroyParts())
				p.detach(source);
		}
		return wound;
	}

	public float efficiency(String bodyPart) {
		BodyPart part = getPart(bodyPart);
		if (part == null)
			return 0;
		return part.efficiency();
	}

	@Override
	public Iterable<? extends Viewable> viewCol() {
		return bodyParts.values();
	}

	@Override
	public String title() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int pageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String page(int pageNum, boolean full) {
		switch (pageNum) {
		default:
			return viewPage(full);
		}
	}

	@Override
	public String toString() {
		Array<BodyPart> arr = new Array<BodyPart>(coreParts);
		Iterator<BodyPart> it = arr.iterator();
		String str = it.next().toString();
		while (it.hasNext())
			str += "\n" + it.next().toString();
		return str;
	}

}
