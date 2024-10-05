package dnd.v5.character;;

public enum Ability {
    STR("Strength"),
    DEX("Dexterity"),
    CON("Constitution"),
    INT("Inteligence"),
    WIS("Wisdom"),
    CHA("Charisma");

    public final String name;

    Ability(String name) {
        this.name = name;
    }
}
