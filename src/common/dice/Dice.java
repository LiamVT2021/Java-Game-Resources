package common.dice;

import java.util.function.IntSupplier;

@FunctionalInterface
public interface Dice extends IntSupplier {

    @Override
    public default int getAsInt() {
        return roll();
    }

    public int roll();

    public default int disadvantage() {
        int a = roll(), b = roll();
        return a < b ? a : b;
    }

    public default int advantage() {
        int a = roll(), b = roll();
        return a > b ? a : b;
    }

}
