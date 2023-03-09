package common.dice;

import java.util.stream.Collectors;

import common.prim.PrimMap;

public class DiceSet<D extends Enum<D> & Die> implements Dice {

    private final PrimMap.Byte<D> map;
    private String str;

    public DiceSet(Class<D> clazz) {
        map = new PrimMap.Byte<>(clazz);
    }

    public int roll() {
        return map.mapToInt((die, count) -> die.roll(count.byteValue())).all().sum();
    }

    public void setDice(int count, D die) {
        str = null;
        map.set(die, (byte) count);
    }

    public DiceSet<D> withDice(int count, D die) {
        setDice(count, die);
        return this;
    }

    public void addDice(int count, D die) {
        setDice(count + map.get(die), die);
    }

    private static String diceStr(Object die, byte count) {
        if (count == 0)
            return "";
        return (count < 0 ? "" : "+") + count + die;
    }

    public String toString() {
        if (str == null)
            str = map.map(DiceSet::diceStr).all().collect(Collectors.joining());
        return str;
    }

}
