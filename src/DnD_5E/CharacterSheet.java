package DnD_5E;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import common.Builders;
import common.Sheet;
import common.Enum.PrimMap;
import common.util.Vacuous;

public class CharacterSheet implements Sheet<Ability> {

    public final PrimMap.Int<Ability> abilityScores = new PrimMap.Int<>(Ability.class);
    public final PrimMap.Bool<Ability> saveProficencies = new PrimMap.Bool<>(Ability.class);
    public final PrimMap.Bool<Skill> skillProficencies = new PrimMap.Bool<>(Skill.class);

    private int level = 0;

    public int proficencyBonus(boolean isProficent) {
        return isProficent ? (7 + level) / 4 : 0;
    }

    @Override
    public int getAttribute(Ability ability) {
        return abilityScores.getInt(ability);
    }

    @Override
    public void forEachAttribute(BiConsumer<? super Ability, Number> func) {
        abilityScores.forEach(func);
    }

    public int saveMod(Ability ability) {
        return getAttMod(ability) + proficencyBonus(saveProficencies.getBool(ability));
    }

    public int skillMod(Skill skill) {
        return getAttMod(skill.ability) + proficencyBonus(skillProficencies.get(skill));
    }

    public String toString(boolean all) {
        return Builders.buildString(builder -> {
            builder.append("Ability Scores:\n");
            builder.append(this.attTable(false));
            builder.append("\nSaving Throws:\n");
            Consumer<Ability> save = ability -> builder.append(Sheet.modString(ability.toString(), saveMod(ability)));
            saveProficencies.ifElse(save, all ? save : Vacuous::NOOP);
            builder.append("\nSkills:\n");
            Consumer<Skill> prof = skill -> builder.append(skill.toString(skillMod(skill)));
            skillProficencies.ifElse(prof, all ? prof : Vacuous::NOOP);
        });
    }

}
