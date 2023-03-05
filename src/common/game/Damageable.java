package common.game;

public interface Damageable<T extends Enum<T>> {

    void damage(int dmg);

    int damage(int dmg, T type);

}
