package rand.dice;

import java.util.Arrays;

public class Constant implements Rollable {

    private final int con;

    public Constant(int constant) {
        con = constant;
    }

    // ROLLS

    @Override
    public int roll() {
        return con;
    }

    @Override
    public int[] array(int rollCount) {
        int[] arr;
        if (rollCount > 0) {
            arr = new int[rollCount];
            Arrays.fill(arr, con);
        } else if (rollCount < 0) {
            arr = new int[-rollCount];
            Arrays.fill(arr, -con);
        } else
            arr = new int[0];
        return arr;
    }

    @Override
    public int sumOf(int rollCount) {
        return rollCount * con;
    }

    @Override
    public int minOf(int rollCount) {
        return rollCount < 0 ? -con : con;
    }

    @Override
    public int maxOf(int rollCount) {
        return rollCount < 0 ? -con : con;
    }

    // INFO

    @Override
    public int min() {
        return con;
    }

    @Override
    public int max() {
        return con;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public String range() {
        return String.valueOf(con);
    }

}
