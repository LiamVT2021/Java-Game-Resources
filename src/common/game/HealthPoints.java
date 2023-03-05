package common.game;

import java.text.NumberFormat;

public interface HealthPoints<T extends Enum<T>> extends Damageable<T> {

    int HP();

    int maxHP();

    int heal(int hp);

    default float fracHP() {
        return HP() / maxHP();
    }

    default String percentHP() {
        return NumberFormat.getPercentInstance().format(fracHP());
    }

}
