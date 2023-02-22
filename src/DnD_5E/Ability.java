package DnD_5E;

import common.Enum.Attribute;

public enum Ability implements Attribute<Ability> {

    STR("Strength"),
    DEX("Dexterity"),
    CON("Constitution"),
    INT("Intelligence"),
    WIS("Wisdom"),
    CHA("Charisma");

    public final String name;

    Ability(String name) {
        this.name = name;
    }

    @Override
    public int baseValue() {
        return 10;
    }

    @Override
    public int mod(Number value) {
        return value.intValue() / 2 - 5;
    }

    public String fullName() {
        return name;
    }

}
