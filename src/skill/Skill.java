package skill;

import java.util.List;
import java.util.stream.IntStream;

public class Skill {

    private final SkillNode<?> skillNode;
    private final int[] levels;
    private final List<?> perks;

    public Skill(SkillNode<?> skillNode, int[] levels) {
        this.skillNode = skillNode;
        this.levels = levels;
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

    public int level() {
        return IntStream.of(levels).sum();
    }

    public int value() {
        return perks.size();
    }

}
