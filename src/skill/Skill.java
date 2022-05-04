package skill;

import java.util.List;
import java.util.stream.IntStream;

public class Skill<U> {

    private SkillNode<?> skillNode;
    private int[] levels;
    private List<?> perks;

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
