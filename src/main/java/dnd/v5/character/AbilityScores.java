package dnd.v5.character;

import java.util.EnumSet;

import common.prim.PrimEnumMap.ByteEnumMap;;

public class AbilityScores extends ByteEnumMap<AbilityScores.Type> {

    public static enum Type {
        STR("Strength"),
        DEX("Dexterity"),
        CON("Constitution"),
        INT("Inteligence"),
        WIS("Wisdom"),
        CHA("Charisma");

        public final String name;

        Type(String name) {
            this.name = name;
        }
    }

    private final EnumSet<Type> savingThrows = EnumSet.noneOf(Type.class);

    public AbilityScores(byte STR, byte DEX, byte CON, byte INT, byte WIS, byte CHA) {
        super(Type.class, STR, DEX, CON, INT, WIS, CHA);
    }

    public byte getMod(Type ability) {
        return (byte) (get(ability) / 2 - 5);
    }

    public AbilityScores withSavingThrow(Type ability) {
        savingThrows.add(ability);
        return this;
    }

    public boolean hasSavingThrow(Type ability) {
        return savingThrows.contains(ability);
    }

    public byte getSavingThrow(Type ability, byte prof) {
        return (byte) (getMod(ability) + (hasSavingThrow(ability) ? prof : 0));
    }

}
