package DnD_5E;

import common.dice.Dice;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.IntSupplier;

import static common.dice.D20.*;
import static DnD_5E.Common._2d6;
import static DnD_5E.DamageType.*;
import static DnD_5E.WeaponProperties.*;

public enum Weapon {

    Club(10, d4, Bludgeoning, 2, Light),
    // Dagger(d4, Piercing, 1, 200, 20, 60),
    Greatclub(20, d8, Bludgeoning, 8, TwoHanded),
    // Handaxe(d6, Slashing, 2, 500, 20, 60),
    // Javlien(d6, Piercing, 2, 50, 30, 120);
    // Light Hammer
    Mace(500, d6, Bludgeoning, 4),
    // Quarterstaff(20,d6,Bludgeoning,4),
    Sicle(100, d4, Slashing, 2, Light),
    // Spear

    // Battleaxe
    Flail(1000, d8, Bludgeoning, 2),
    Glaive(2000, d10, Slashing, 6, Heavy, Reach, TwoHanded),
    Greataxe(3000, d12, Slashing, 7, Heavy, TwoHanded),
    Greatsword(5000, _2d6, Slashing, 6, Heavy, TwoHanded),
    Halberd(2000, d10, Slashing, 6, Heavy, Reach, TwoHanded),
    // Lance
    // LongSword
    Maul(1000, _2d6, Bludgeoning, 10, Heavy, TwoHanded),
    Morningstar(1500, d8, Piercing, 4),
    Pike(5000, d10, Piercing, 18, Heavy, Reach, TwoHanded),
    ;

    private final Dice dice;
    public final DamageType type;
    private final short wieght, cost;// , shortRange, longRange;
    private final EnumSet<WeaponProperties> properties;

    private Weapon(int cost, Dice dice, DamageType damageType, int wieght, WeaponProperties... properties) {
        // this(dice, damageType, wieght, cost, 0, 0);
        // }

        // private Weapon(Dice dice, DamageType damageType, int wieght, int cost, int
        // shortRange, int longRange) {
        this.dice = dice;
        type = damageType;
        this.wieght = (short) wieght;
        this.cost = (short) cost;
        // this.shortRange = (short) shortRange;
        // this.longRange = (short) longRange;
        this.properties = EnumSet.copyOf(Set.of(properties));
    }

    public int attackMod(CharacterSheet sheet) {
        int strMod = sheet.getAttMod(Ability.STR);
        return properties.contains(Finesse) ? Math.max(sheet.getAttMod(Ability.DEX), strMod) : strMod;
    }

}
