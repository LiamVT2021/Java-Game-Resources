package dice;

import java.util.Random;
import java.util.function.IntSupplier;

public enum Die implements IntSupplier {

    d4(4), d6(6), d8(8), d10(10), d12(12), d20(20), d100(100);

    private static final Random rand = new Random();
    private final int r;
    private final String str;

    private Die(int size) {
        if (size < 2)
            throw new IllegalArgumentException("Die size must be at least 2");
        r = size - 1;
        str = "d" + size;
    }

    @Override
    public int getAsInt() {
        return roll();
    }

    public int roll() {
        return rand.nextInt(r) + 1;
    }

    public int roll(int count) {
        int i = Math.abs(count), ret = i;
        for (; i > 0; i--)
            ret += rand.nextInt(r);
        return count >= 0 ? ret : -ret;
    }

    public String toString() {
        return str;
    }

}
