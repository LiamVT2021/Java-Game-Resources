package skill;

import ui.Page;
import ui.Viewable;

public class SkillView<T> implements Viewable, Page {

//	private SkillADT skill;
	private T tree;
//	private int level;
	private SkillEntry<T> entry;

//	public SkillView(SkillADT skill, Sheet sheet, int level) {
//		this.skill = skill;
//		this.sheet = sheet;
//		this.level = level;
//	}

	public SkillView(T skillTree, SkillEntry<T> skillEntry) {
		tree = skillTree;
		entry = skillEntry;
	}

	@Override
	public String page(boolean full) {
		String page = lineString(full);
		SkillADT<T> skill = entry.skill();
		page += "\n" + skill.effects(entry.level());
//		if (full)
//			page += "\n" + skill.reqs(tree, entry);
		return page;
	}

	@Override
	public String lineString(boolean full) {
		if (full)
			return null;// Not yet implemented
		else
			return entry.name(true) + ": " + entry.level();
	}

}
