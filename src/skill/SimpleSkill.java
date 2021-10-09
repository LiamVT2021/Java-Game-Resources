package skill;

import java.util.function.IntFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import check.IntBiCheck;
import check.IntCheck;

public class SimpleSkill<T> extends SkillADT<T> {

	private IntBiCheck<? super T, ? super SkillEntry<T>> aReqs;
	private IntBiCheck<? super T, ? super SkillEntry<T>> uReqs;

	public SimpleSkill(String skillName,
			IntFunction<String> effectFunc,
			ToIntFunction<T> aquireReqs,
			ToIntFunction<T> unlockReqs) {
		super(skillName, effectFunc);
		aReqs = IntCheck.cast(aquireReqs).biSum(null);
		uReqs = IntCheck.cast(unlockReqs).biSum(null);
	}

	public SimpleSkill(String skillName,
			IntFunction<String> effectFunc,
			ToIntBiFunction<T, SkillEntry<T>> aquireReqs,
			ToIntBiFunction<T, SkillEntry<T>> unlockReqs) {
		super(skillName, effectFunc);
		aReqs = IntBiCheck.cast(aquireReqs);
		uReqs = IntBiCheck.cast(unlockReqs);
	}

	@Override
	public boolean canAquire(T tree, SkillEntry<T> skillEntry) {
		return aReqs.test(tree, skillEntry, skillEntry.level() + 1);
	}

	@Override
	public int unlockLevel(T tree, SkillEntry<T> skillEntry) {
		return uReqs.applyAsInt(tree, skillEntry);
	}
}
