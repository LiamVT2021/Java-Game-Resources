package skill;

import static skill.SkillReqs.orderedReqs;

import java.util.function.IntFunction;
import java.util.function.Predicate;

public class SimpleSkillNode<U extends SkillUser> extends SkillNodeADT<U> {

    private final String name;
    private final Predicate<U>[] unlockReqs;
    private final Predicate<U>[] aquireReqs;

    public SimpleSkillNode(String skillId, String name, IntFunction<String> descFunc,
            Predicate<U>[] unlockReqs, Predicate<U>[] aquireReqs) {
        super(skillId, descFunc);
        this.name = name;
        this.unlockReqs = unlockReqs;
        this.aquireReqs = aquireReqs;
    }

    @Override
    public String name(int value) {
        return name + " " + value;
    }

    @Override
    public int getLevel(U user) {
        return orderedReqs(user, unlockReqs);
    }

    @Override
    public int maxAquire(U user) {
        return orderedReqs(user, aquireReqs);
    }

    @Override
    public SkillADT newSkill() {
        return new SimpleSkill(this, 0);
    }

}
