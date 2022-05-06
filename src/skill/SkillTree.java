package skill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class SkillTree<U extends SkillUser> {

    private final SkillNode<U>[] roots;
    private final Map<String, SkillNode<U>> allSkills;

    public SkillTree(Iterable<SkillNodeBuilder<U>> skills, IntFunction<ToIntFunction<U>[]> arrGen,
            IntFunction<SkillNode<U>[]> rootArrGen) {
        allSkills = new HashMap<>();
        List<SkillNode<U>> rootList = List.of();
        for (SkillNodeBuilder<U> skill : skills) {
            SkillNode<U> node = skill.toNode(arrGen);
            for (String id : skill.prev()) {
                SkillNode<U> prev = allSkills.get(id);
                if (prev == null)
                    throw new Error("missing prev SkillNode");
                node.addNext(prev);
            }
            allSkills.put(node.id, node);
            if (skill.isRoot())
                rootList.add(node);
        }
        roots = rootList.toArray(rootArrGen);
    }

    public SkillNode<U> getSkillNode(String skillId) {
        return allSkills.get(skillId);
    }

    public Stream<SkillNode<U>> roots(){
        return Stream.of(roots);
    }
}
