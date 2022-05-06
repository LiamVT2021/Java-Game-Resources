package skill;

import java.util.HashMap;
import java.util.Map;

public abstract class SkillUser {

    private final Map<String, Skill> skills;

    public SkillUser() {
        skills = new HashMap<>();
    }

    protected Skill getSkill(String skillId) {
        return skills.get(skillId);
    }

    public int getSkillLevel(String skillId) {
        return getSkill(skillId).level();
    }

    public int getSkillValue(String skillId) {
        return getSkill(skillId).value();
    }

    protected abstract SkillUser otherUser(String userId);

    protected abstract SkillTree<? extends SkillUser> skillTree();

}
