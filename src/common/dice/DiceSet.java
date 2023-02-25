package common.dice;

import common.Enum.PrimMap;

public class DiceSet implements Dice {

    private final PrimMap.Byte<Die> map = new PrimMap.Byte<>(Die.class);
    private String str;

    public int roll() {
        return map.sum((die, count) -> die.roll(count.byteValue()));
    }

    public DiceSet setDice(int count, Die die) {
        str = null;
        map.set(die, (byte) count);
        return this;
    }

    public DiceSet addDice(int count, Die die) {
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