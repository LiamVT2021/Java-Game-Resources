package rand.dice;

import java.util.Arrays;
import java.util.stream.IntStream;

public interface Rollable {

    /**
     * @return the result of rolling this
     */
    int roll();

    /**
     * @return the minimum possible result of this
     */
    int min();

    /**
     * @return the maximum possible result of this
     */
    int max();

    /**
     * @return if rolling this will always return the same result
     */
    default boolean isConstant() {
        return min() == max();
    }

    /**
     * @return the range of possible vaules that can be returned by this "min-max"
     *         just the expected result if constant
     */
    default String range() {
        int min = min(), max = max();
        return min == max ? String.valueOf(min) : min() + "-" + max();
    }

    /**
     * @return a String expressing this as a dice roll ex: "d100+2d12+8"
     */
    default String diceStr() {
        return range();
    }

    /**
     * @param rollCount the number of times this is rolled
     * @return an int[] of the results of rolling this,
     *         negates rolls if rollCount < 0
     */
    default int[] array(int rollCount) {
        int[] arr;
        if (rollCount > 0) {
            arr = new int[rollCount];
            if (isConstant())
                Arrays.fill(arr, min());
            else
                for (int i = 0; i < rollCount; i++)
                    arr[i] = roll();

        } else if (rollCount < 0) {
            arr = new int[-rollCount];
            if (isConstant())
                Arrays.fill(arr, -min());
            else
                for (int i = 0; i < -rollCount; i++)
                    arr[i] = -roll();
        } else
            arr = new int[0];
        return arr;
    }

    /**
     * @param rollCount the number of times this is rolled
     * @return an IntStream of the results of rolling this,
     *         negates rolls if rollCount < 0
     */
    default IntStream stream(int rollCount) {
        return IntStream.of(array(rollCount));
    }

    /**
     * @param rollCount the number of times this is rolled
     * @return the sum of roll results,
     *         negates rolls if rollCount < 0
     */
    default int sum(int rollCount) {
        return isConstant() ? rollCount * min() : stream(rollCount).sum();
    }

    /**
     * @param rollCount the number of times this is rolled
     * @return the minimum roll result,
     *         negates rolls if rollCount < 0
     */
    default int min(int rollCount) {
        return stream(rollCount).min().getAsInt();
    }

    /**
     * @param rollCount the number of times this is rolled
     * @return the maximum roll result,
     *         negates rolls if rollCount < 0
     */
    default int max(int rollCount) {
        return stream(rollCount).max().getAsInt();
    }

}
