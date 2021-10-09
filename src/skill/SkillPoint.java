package skill;

import sheet.Sheet;

public interface SkillPoint {

	public boolean mustSpend();

	public boolean autoSpend();

	public SkillADT<?> next(Sheet sheet);

	public SkillADT<?>[] canAquire(Sheet sheet);

	public SkillPoint[] canSwap(Sheet sheet);
}
