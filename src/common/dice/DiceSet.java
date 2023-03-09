package common.dice;

import java.util.stream.Collectors;

import common.prim.PrimMap;

public class DiceSet implements Dice {

    private final PrimMap.Byte<Die> map = new PrimMap.Byte<>(Die.class);
    private String str;

    public int roll() {
        return map.mapToInt((die, count) -> die.roll(count.byteValue())).all().sum();
    }

    public DiceSet setDice(int count, Die die) {
        str = null;
        map.set(die, (byte) count);
        return this;
    }

    public DiceSet addDice(int count, Die die) {
        return setDice(count + map.get(die), die);
    }

    private static String diceStr(Die die, byte count) {
        if (count == 0)
            return "";
        return (count < 0 ? "" : "+") + count + die.toString();
    }

    public String toString() {
        if (str == null)
            str = map.map(DiceSet::diceStr).all().collect(Collectors.joining());
        return str;
    }

}
