package dice;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.IntSupplier;

public class Dice implements IntSupplier {

    @Override
    public int getAsInt() {
        return roll();
    }

    private final Map<Die, Integer> map = new EnumMap<>(Die.class);
    public int mod = 0;

    public int roll() {
        return map.entrySet().parallelStream().mapToInt(e -> e.getKey().roll(e.getValue())).sum() + mod;
    }

    public Dice setDice(int count, Die die) {
        if (count == 0)
            map.remove(die);
        else
            map.put(die, count);
        return this;
    }

    public Dice addDice(int count, Die die) {
        return setDice(count + map.getOrDefault(die, 0), die);
    }

    private static String forceSign(int i) {
        return (i < 0 ? "" : "+") + i;
    }

    public String toString() {
        if (map.isEmpty())
            return String.valueOf(mod);
        StringBuilder str = new StringBuilder();
        map.forEach((die, count) -> {
            str.append(forceSign(count));
            str.append(die.toString());
        });
        str.append(forceSign(mod));
        return str.charAt(0) != '+' ? str.toString() : str.substring(1);
    }

}
