package rand.dice;

import java.util.Random;

public class Range implements Rollable {
    private static final Random RAND = new Random();

    private int min;
    private int max;

    public Range(int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("Max cannot be less than Min");
        this.min = min;
        this.max = max + 1;
    }

    @Override
    public int roll() {
        return RAND.nextInt(min, max);
    }

    @Override
    public int min() {
        return min;
    }

    @Override
    public int max() {
        return max - 1;
    }

    @Override
    public String dice() {
        if (max == min + 1)
            return String.valueOf(min);
        String ret = "d" + (max - min);
        int mod = min - 1;
        if (mod > 0)
            ret += "+" + mod;
        else if (mod < 0)
            ret += mod;
        return ret;
    }
}
