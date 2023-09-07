package common.dice;

import java.util.Arrays;
import java.util.Random;

public class Dice implements Roller {

    private static final Random rand = new Random();
    private final short[] sizes;
    private final int mod;

    public Dice(int mod, short... sizes) {
        for (int size : sizes)
            if (size < 2)
                throw new IllegalArgumentException("die size must be at least 2, is: " + size);
        this.sizes = sizes;
        this.mod = mod;
        Arrays.sort(this.sizes);
    }

    @Override
    public int rand() {
        int ret = 0;
        for (short s : sizes)
            ret += rand.nextInt(s);
        return ret;
    }

    @Override
    public int mod() {
        return mod + sizes.length;
    }

    @Override
    public Integer rangeMin() {
        return mod();
    }

    @Override
    public Integer rangeMax() {
        int max = 0;
        for (short size : sizes)
            max += size;
        return max;
    }

    public String toString() {
        if (sizes.length == 0)
            return String.valueOf(mod);
        StringBuilder ret = new StringBuilder();
        int c = 1, i = sizes.length - 1, d = sizes[i--];
        while (i >= 0) {
            int s = sizes[i--];
            if (s != d) {
                ret.append(ret.isEmpty() ? dieString(c, d) : "+" + dieString(c, d));
                c = 1;
                d = s;
            } else
                c++;
        }
        ret.append(ret.isEmpty() ? dieString(c, d) : "+" + dieString(c, d));
        if (mod > 0)
            ret.append("+" + mod);
        else if (mod < 0)
            ret.append(String.valueOf(mod));
        return ret.toString();
    }

    private static String dieString(int count, int size) {
        String ret = "d" + size;
        return (count > 1 ? count + ret : ret);
    }

}
