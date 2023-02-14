package dice;

import java.util.function.IntSupplier;

import enums.PrimMap;

public class Dice implements IntSupplier {

    @Override
    public int getAsInt() {
        return roll();
    }

    private final PrimMap.Byte<Die> map = new PrimMap.Byte<>(Die.class);
    private String str;

    public int roll() {
        return map.sum((die, count) -> die.roll(count.byteValue()));
    }

    public Dice setDice(int count, Die die) {
        str = null;
        map.set(die, count);
        return this;
    }

    public Dice addDice(int count, Die die) {
        return setDice(count + map.getByte(die), die);
    }

    private static String forceSign(byte i) {
        return (i < 0 ? "" : "+") + i;
    }

    public String toString() {
        if (str != null)
            return str;
        if (map.isEmpty())
            return null;
        StringBuilder builder = new StringBuilder();
        for (Die die : Die.values()) {
            builder.append(forceSign(map.getByte(die)));
            builder.append(die.toString());
        }
        str = builder.charAt(0) != '+' ? builder.toString() : builder.substring(1);
        return str;
    }

}
