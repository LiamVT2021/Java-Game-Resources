package skill;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class SkillNodeBuilder<U extends SkillUser> {

    private String id;
    private IntFunction<String> nameFunc;
    private IntFunction<String> descFunc;
    private List<ToIntFunction<U>> unlockList;
    private List<ToIntFunction<U>> aquireList;
    private List<String> prev;
    private boolean root;

    public SkillNodeBuilder() {
        unlockList = List.of();
        aquireList = List.of();
        prev = List.of();
    }

    public SkillNode<U> toNode(IntFunction<ToIntFunction<U>[]> arrGen) {
        return new SkillNode<>(id, nameFunc, descFunc, unlockList.toArray(arrGen), unlockList.toArray(arrGen));
    }

    public Iterable<String> prev() {
        return prev;
    }

    public boolean isRoot() {
        return root;
    }

    //

    public void withName(IntFunction<String> nameFunction) {
        nameFunc = nameFunction;
    }

    public void withName(String name) {
        nameFunc = value -> name + ": " + value;
    }

    //

    private ToIntFunction<U> ordered(Predicate<U>... predicates) {
        return (predicates == null || predicates.length == 0) ? null : user -> SkillReqs.orderedReqs(user, predicates);
    }

    //

    public boolean addUnlock(ToIntFunction<U> intFunc) {
        return unlockList.add(intFunc);
    }

    public boolean addUnlock(Predicate<U>... predicates) {
        return addUnlock(ordered(predicates));
    }

    public boolean addAquire(ToIntFunction<U> intFunc) {
        return aquireList.add(intFunc);
    }

    public boolean addAquire(Predicate<U>... predicates) {
        return addAquire(ordered(predicates));
    }

}
