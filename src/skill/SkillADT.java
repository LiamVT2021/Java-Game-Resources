package skill;

import java.util.function.IntFunction;
import java.util.stream.Stream;

import util.Named;

public abstract class SkillADT<T> implements Named {

	private String name;
	private IntFunction<String> effects;

	public SkillADT(String skillName,
			IntFunction<String> effectFunc) {
		name = skillName;
		effects = effectFunc;
	}

	public String name() {
		return name;
	}

	@Override
	public String name(boolean full) {
		return name;
	}

	public String effects(int level) {
		return effects.apply(level);
	}

	public abstract boolean canAquire(T tree,
			SkillEntry<T> skillEntry);

	public Stream<SkillPoint> swappable(SkillEntry<T> skillEntry) {
		return skillEntry.swappable();
	}

	public abstract int unlockLevel(T tree, SkillEntry<T> skillEntry);

//	public abstract String reqs(T tree, SkillEntry<T> skillEntry);

}
