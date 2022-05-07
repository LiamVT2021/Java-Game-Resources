package skill;

import java.util.List;

public abstract class SkillADT {

    private final SkillNodeADT<?> skillNode;
    private final List<?> perks;

    public SkillADT(SkillNodeADT<?> skillNode) {
        this.skillNode = skillNode;
        perks = List.of();
    }

    public String id() {
        return skillNode.id;
    }

    public String name() {
        return skillNode.name(value());
    }

    public String description() {
        return skillNode.description(value());
    }

    public abstract int level();

    public int value() {
        return level() + perks.size();
    }

}
