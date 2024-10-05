package dnd.v5.character;

import static dnd.v5.character.Ability.*;

public enum Skill {
    Acrobatics(DEX);

    public final Ability ability;

    Skill(Ability ability) {
        this.ability = ability;
    }
}
