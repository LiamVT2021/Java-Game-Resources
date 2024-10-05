package dnd.v5.character;

import java.util.EnumSet;

import common.prim.PrimEnumMap.ByteEnumMap;

public class Sheet {
    private byte proficencyBonus;
    private ByteEnumMap<Ability> abilityScores;
    private final EnumSet<Ability> savingThrows = EnumSet.noneOf(Ability.class);

    public Sheet withAbilityScores(byte STR, byte DEX, byte CON, byte INT, byte WIS, byte CHA) {
        abilityScores = new ByteEnumMap<>(Ability.class, STR, DEX, CON, INT, WIS, CHA);
        return this;
    }

    public Sheet withAbilityScore(Ability ability, byte score) {
        abilityScores.set(ability, score);
        return this;
    }

    public int getAbilityMod(Ability ability) {
        return abilityScores.get(ability) / 2 - 5;
    }

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

}
