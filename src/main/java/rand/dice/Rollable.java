package rand.dice;

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
     * @param rollCount the number of times this is rolled
     * @return an int[] of the results of rolling this,
     *         negates rolls if rollCount < 0
     */
    default int[] array(int rollCount) {
        int[] arr;
        if (rollCount < 0) {
            arr = new int[-rollCount];
            for (int i = 0; i < -rollCount; i++)
                arr[i] = -roll();
        } else {
            arr = new int[rollCount];
            for (int i = 0; i < rollCount; i++)
                arr[i] = roll();
        }
        return arr;
    }

    /**
     * @return the range of possible vaules that can be returned by this "min-max"
     */
    default String range() {
        return min() + "-" + max();
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
        return stream(rollCount).sum();
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
