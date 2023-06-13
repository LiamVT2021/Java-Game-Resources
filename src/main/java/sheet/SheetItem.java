package sheet;

import ui.Viewable;

public abstract class SheetItem implements Viewable<Sheet>{
	
	@Override
	public String lineString(Sheet sheet) {
		return name() + ": " + getValue(sheet);
	}

	public abstract String name();

	protected int level;

	public int getValue(Sheet sheet) {
		return level;
	}

}
