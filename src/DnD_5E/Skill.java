package DnD_5E;

import static DnD_5E.Ability.*;

import common.Sheet;

public enum Skill {

    Acrobatics(DEX),
    Animal_Handling(WIS),
    Arcana(INT),
    Athletics(STR);

    public final Ability ability;

    Skill(Ability ability) {
        this.ability = ability;
    }

    public String toString(int mod) {
        return Sheet.modString(toString() + '(' + ability + ')', mod);
    }

}
