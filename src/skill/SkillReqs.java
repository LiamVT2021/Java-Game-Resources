package skill;

import java.util.function.Predicate;

public interface SkillReqs {

    static <U extends SkillUser> Predicate<U> skillLevel(String skillId, int level) {
        return user -> user.getSkillLevel(skillId) >= level;
    }

    static <U extends SkillUser> Predicate<U> skillValue(String skillId, int value) {
        return user -> user.getSkillValue(skillId) >= value;
    }

    static <U extends SkillUser> Predicate<U> skillLevel(String userId, String skillId, int level) {
        return user -> user.otherUser(userId).getSkillLevel(skillId) >= level;
    }

    static <U extends SkillUser> Predicate<U> skillValue(String userId, String skillId, int value) {
        return user -> user.otherUser(userId).getSkillValue(skillId) >= value;
    }

    static <U extends SkillUser> int orderedReqs(U user, Predicate<U>... predicates) {
        int i = 0;
        for (Predicate<U> pred : predicates) {
            if (!pred.test(user))
                return i;
            else
                i++;
        }
        return i;
    }

}
