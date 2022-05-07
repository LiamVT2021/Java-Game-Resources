package skill;

import java.util.stream.IntStream;

public class Skill extends SkillADT {

    private final int[] levels;

    public Skill(SkillNode<?> skillNode, int[] levels) {
        super(skillNode);
        this.levels = levels;
    }

    public int level() {
        return IntStream.of(levels).sum();
    }

}
