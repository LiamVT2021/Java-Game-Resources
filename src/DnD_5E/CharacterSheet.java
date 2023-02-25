package DnD_5E;

import java.util.EnumSet;
import java.util.function.BiConsumer;

import common.Builders;
import common.Sheet;
import common.prim.PrimMap;

public class CharacterSheet implements Sheet<Ability> {

    public final PrimMap.Int<Ability> abilityScores = new PrimMap.Int<>(Ability.class);
    public final EnumSet<Ability> saveProficencies = EnumSet.noneOf(Ability.class);
    public final EnumSet<Skill> skillProficencies = EnumSet.noneOf(Skill.class);
    public final EnumSet<Weapon> weaponProficencies = EnumSet.noneOf(Weapon.class);

    private int level = 0;

    public int proficencyBonus(boolean isProficent) {
        return isProficent ? (7 + level) / 4 : 0;
    }

    @Override
    public int getAttribute(Ability ability) {
        return abilityScores.get(ability);
    }

    @Override
    public void forEachAttribute(BiConsumer<? super Ability, Integer> func) {
        abilityScores.forEach(func);
    }

    public int saveMod(Ability ability) {
        return getAttMod(ability) + proficencyBonus(saveProficencies.contains(ability));
    }

    public int skillMod(Skill skill) {
        return getAttMod(skill.ability) + proficencyBonus(skillProficencies.contains(skill));
    }

    public String toString(boolean all) {
        return Builders.buildString(builder -> {
            builder.append("Ability Scores:\n");
            builder.append(this.attTable(false));
            builder.append("\nSaving Throws:\n");
            (all ? EnumSet.allOf(Ability.class) : saveProficencies)
                    .forEach(ability -> builder.append(Sheet.modString(ability.toString(), saveMod(ability))));
            builder.append("\nSkills:\n");
            (all ? EnumSet.allOf(Skill.class) : skillProficencies)
                    .forEach(skill -> builder.append(skill.toString(skillMod(skill))));
        });
    }

}
