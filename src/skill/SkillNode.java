package skill;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class SkillNode<U extends SkillUser> extends SkillNodeADT<U> {

    public final IntFunction<String> nameFunc;
    protected final ToIntFunction<U>[] unlockSets;
    protected final ToIntFunction<U>[] aquireSets;

    public SkillNode(String skillId, IntFunction<String> nameFunc, IntFunction<String> descFunc,
            ToIntFunction<U>[] unlockSets, ToIntFunction<U>[] aquireSets) {
        super(skillId, descFunc);
        this.nameFunc = nameFunc;
        this.unlockSets = unlockSets;
        this.aquireSets = aquireSets;
    }

    public String name(int value) {
        return nameFunc.apply(value);
    }

    //

    private int sum(U user, ToIntFunction<U>[] set) {
        return Stream.of(set).parallel().unordered().mapToInt(u -> u.applyAsInt(user)).sum();
    }

    public int getLevel(U user) {
        return sum(user, unlockSets);
    }

    public int maxAquire(U user) {
        return sum(user, aquireSets);
    }

    //

    public Skill newSkill() {
        return new Skill(this, new int[unlockSets.length]);
    }

}
