package old.proficiency;

import old.sheet.Sheet;
import old.sheet.SheetItem;
import old.ui.Openable;

public abstract class ProficiencyADT extends SheetItem implements Openable<Sheet> {

	public abstract String fullName();

	@Override
	public String lineString(Sheet sheet) {
		return fullName() + ": " + getValue(sheet);
	}

	@Override
	public String str(Sheet sheet) {
		return name() + ": " + getValue(sheet);
	}

//	public abstract int valMod(Sheet sheet);
//
//	@Override
//	public int getValue(Sheet sheet) {
//		return level + valMod(sheet);
//	}

	public int maxLevel() {
		return 100;
	}

	public int maxValue() {
		return 125;
	}

	public abstract int minValue(Sheet sheet);
	
//	public abstract void allSkills();

}
