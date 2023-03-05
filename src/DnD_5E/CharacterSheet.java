package DnD_5E;

import java.util.EnumSet;
import java.util.function.BiConsumer;

import common.Builders;
import common.game.HealthPoints;
import common.game.Sheet;
import common.prim.PrimMap;

public class CharacterSheet implements Sheet<Ability>, HealthPoints<DamageType> {

    public final PrimMap.Int<Ability> abilityScores = new PrimMap.Int<>(Ability.class);
    public final EnumSet<Ability> saveProficencies = EnumSet.noneOf(Ability.class);
    public final EnumSet<Skill> skillProficencies = EnumSet.noneOf(Skill.class);
    public final EnumSet<Weapon> weaponProficencies = EnumSet.noneOf(Weapon.class);
    private final PrimMap.Float<DamageType> resistances = new PrimMap.Float<>(DamageType.class);

    private int level, hp, maxHp, tempHp;

    @Override
    public int HP() {
        return hp + tempHp;
    }

    @Override
    public int maxHP() {
        return maxHp;
    }

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

    public void setResistance(ResistanceType res, DamageType type){
        resistances.set(type, res.mod);
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

    @Override
    public void damage(int dmg) {
        hp -= dmg;
    }

    @Override
    public int damage(int dmg, DamageType type) {
        dmg = (int) resistances.getProduct(type, dmg);
        damage(dmg);
        return dmg;
    }

    @Override
    public int heal(int heal) {
        int old = hp;
        hp += heal;
        if (hp > maxHp)
            hp = maxHp;
        return hp - old;
    }

}
