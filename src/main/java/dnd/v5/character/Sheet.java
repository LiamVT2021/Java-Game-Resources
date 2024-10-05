package dnd.v5.character;

import java.util.EnumSet;

import common.prim.PrimEnumMap.ByteEnumMap;

public class Sheet {
    private byte proficencyBonus;
    private ByteEnumMap<Ability> abilityScores;
    private final EnumSet<Ability> savingThrows = EnumSet.noneOf(Ability.class);
    private final EnumSet<Skill> skillProficency = EnumSet.noneOf(Skill.class);
    private final EnumSet<Skill> skillExpertise = EnumSet.noneOf(Skill.class);

    public Sheet withAbilityScores(byte STR, byte DEX, byte CON, byte INT, byte WIS, byte CHA) {
        abilityScores = new ByteEnumMap<>(Ability.class, STR, DEX, CON, INT, WIS, CHA);
        return this;
    }

    // ABILITY SCORES

    public Sheet withAbilityScore(Ability ability, byte score) {
        abilityScores.set(ability, score);
        return this;
    }

    public int getAbilityMod(Ability ability) {
        return abilityScores.get(ability) / 2 - 5;
    }

    // SAVING THROWS

    public Sheet withSavingThrow(Ability ability) {
        savingThrows.add(ability);
        return this;
    }

    public boolean hasSavingThrow(Ability ability) {
        return savingThrows.contains(ability);
    }

    public int getSavingThrow(Ability ability) {
        return getAbilityMod(ability) + (hasSavingThrow(ability) ? proficencyBonus : 0);
    }

    // SKILLS

    public Sheet withProficency(Skill skill) {
        skillProficency.add(skill);
        return this;
    }

    public Sheet withExpertise(Skill skill) {
        skillExpertise.add(skill);
        return this;
    }

    public Expertise getSkillProf(Skill skill) {
        return skillExpertise.contains(skill) ? Expertise.EXP
                : skillProficency.contains(skill) ? Expertise.PROF : Expertise.NONE;
    }

    public int getSkillMod(Skill skill) {
        return getAbilityMod(skill.ability) + getSkillProf(skill).ordinal() * proficencyBonus;
    }

}
