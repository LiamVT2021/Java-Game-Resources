package body;

public class Head extends BodyPart {

	private BodyPart skull;
	private BodyPart brain;
	private BodyPart jaw;
	private BodyPart lEye;
	private BodyPart rEye;
	private BodyPart nose;
	private BodyPart lEar;
	private BodyPart rEar;
	private BodyPart horns;

	public Head(int HP) {
		super("Head", "Neck", HP);
	}

	@Override
	public boolean hasParts() {
		return true;
	}

	@Override
	public Iterable<BodyPart> allParts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BodyPart> destroyParts() {
		// TODO Auto-generated method stub
		return null;
	}

}
