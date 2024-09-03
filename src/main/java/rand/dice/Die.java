package rand.dice;

import java.util.Random;
import java.util.stream.IntStream;

import common.util.StringUtils;

public class Die implements Rollable {
    private static final Random RAND = new Random();

    private final int[] sides;
    private int min, max;
    private float avg;
    private boolean constant;

    public Die(int[] sides) {
        this.sides = sides;
        min = max = sides[0];
        for (int i = 1; i < sides.length; i++) {
            int s = sides[i];
            if (s < min())
                min = s;
            else if (s > max())
                max = s;
            avg += s;
        }
        avg /= sides.length;
        constant = min == max;
    }

    @Override
    public int roll() {
        return sides[RAND.nextInt(sides.length)];
    }

    @Override
    public int min() {
        return min;
    }

    @Override
    public int max() {
        return max;
    }

    @Override
    public float avg() {
        return avg;
    }

    @Override
    public boolean isConstant() {
        return constant;
    }

    @Override
    public String dice() {
        return StringUtils.join("[", ",", "]", IntStream.of(sides).mapToObj(String::valueOf));
    }

}
