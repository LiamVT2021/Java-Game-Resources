package skill;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public abstract class SkillNodeADT<U extends SkillUser> {

    public final String id;
    private final IntFunction<String> descFunc;
    protected final List<SkillNode<U>> next;
    
    public SkillNodeADT(String skillId, IntFunction<String> descFunc) {
        id = skillId;
        this.descFunc = descFunc;
        next = List.of();
    }

    public abstract String name(int value);

    public String description(int value) {
        return descFunc.apply(value);
    }

    //

    public abstract int getLevel(U user);

    public abstract int maxAquire(U user);

    //

    protected void addNext(SkillNode<U> node) {
        next.add(node);
    }

    public Stream<SkillNode<U>> next() {
        return next.stream();
    }

    //

    public abstract SkillADT newSkill();
}
