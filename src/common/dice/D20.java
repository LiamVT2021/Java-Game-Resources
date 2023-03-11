package common.dice;

import java.util.Random;

public enum D20 implements Die {

    d4(4), d6(6), d8(8), d10(10), d12(12), d20(20), d100(100);

    private static final Random rand = new Random();
    private final int r;

    private D20(int size) {
        if (size < 2)
            throw new IllegalArgumentException("Die size must be at least 2");
        r = size - 1;
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
    
}