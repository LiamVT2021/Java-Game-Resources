package skill;

public class SimpleSkill extends SkillADT {

    public SimpleSkill(SimpleSkillNode<?> skillNode, int level) {
        super(skillNode);
        this.level = level;
    }

    private int level;

    @Override
    public int level() {
        return level;
    }

}
