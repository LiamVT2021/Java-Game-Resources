package skill;

import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class SkillNode<U> {

    public final String id;
    public final IntFunction<String> nameFunc;
    private final IntFunction<String> descFunc;
    protected final ToIntFunction<U>[] unlockSets;
    protected final ToIntFunction<U>[] aquireSets;
    protected final SkillNode<U>[] next;

    public SkillNode(String skillId, IntFunction<String> nameFunc, IntFunction<String> descFunc,
            ToIntFunction<U>[] unlockSets, ToIntFunction<U>[] aquireSets, SkillNode<U>[] next) {
        id = skillId;
        this.nameFunc = nameFunc;
        this.descFunc = descFunc;
        this.unlockSets = unlockSets;
        this.aquireSets = aquireSets;
        this.next = next;
    }

    public String name(int value){
        return nameFunc.apply(value);
    }

    public String description(int value) {
        return descFunc.apply(value);
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

}
