package skill;

import java.util.HashMap;

public class SkillMap implements SkillSet {

	private HashMap<String, Integer> skills;

	public SkillMap() {
		skills = new HashMap<String, Integer>();
	}

	@Override
	public int getLevel(String skillName) {
		return skills.getOrDefault(skillName, 0);

	}

	@Override
	public int getValue(String skillName) {
		return getLevel(skillName);
	}

	@Override
	public String skillPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
