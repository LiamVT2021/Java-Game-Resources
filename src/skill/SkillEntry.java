package skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import util.Named;

public class SkillEntry<T> implements Named {

	private SkillADT<T> skill;
	private int lvl;
	private Map<String, Integer> track;
	private ArrayList<SkillPoint> aquire;

//	public SkillEntry(SkillADT skill, int level,
//			Map<String, Integer> trackMap) {
//		this.skill = skill;
//		lvl = level;
//		track = trackMap;
//	}

	public SkillEntry(SkillADT<T> skill) {
		this.skill = skill;
//		aquire = new Object[];
	}

	@Override
	public String name(boolean full) {
		return skill.name(full);
	}

	public SkillADT<T> skill() {
		return skill;
	}

	public int level() {
		return lvl + aquire.size();
	}

	public void setLevel(int level) {
		lvl = level;
	}

	public Map<String, Integer> trackMap() {
		return track;
	}

	public Map<String, Integer> makeTrack() {
		if (track == null)
			track = new HashMap<String, Integer>();
		return track;
	}

	public SkillView<T> skillView(T tree) {
		return new SkillView<T>(tree, this);
	}

	public String levelUp(T tree) {
		int uLvl = skill.unlockLevel(tree, this);
		if (uLvl <= lvl)
			return null;
		lvl = uLvl;
		return "Unlocked " + skill.name() + " level " + lvl;
	}

	public Stream<SkillPoint> swappable() {
		return null;
	}

}
