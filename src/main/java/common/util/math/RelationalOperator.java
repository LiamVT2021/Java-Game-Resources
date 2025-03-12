package common.util.math;

import java.util.function.Function;

/**
 * Compares two numerical values
 * 
 * @version 2/18/25
 */
public enum RelationalOperator {

    EQ("=", "equal to",
            b -> "exactly " + b,
            (a, b) -> a == b),
    NE("!=", "not equal to",
            b -> "not " + b,
            (a, b) -> a != b),
    LT("<", "less than",
            b -> "less than " + b,
            (a, b) -> a < b),
    LE("<=", "less than or equal to",
            b -> b + "or less",
            (a, b) -> a <= b),
    GT(">", "greater than",
            b -> "more than " + b,
            (a, b) -> a > b),
    GE(">=", "greater than or equal to",
            b -> b + " or more",
            (a, b) -> a >= b);

    /**
     * how to display this operator
     */
    public final String symbol, string;
    private final Function<Number, String> thresh;
    private final Int Int;

    private interface Int {
        boolean compare(int a, int b);
    }

    private RelationalOperator(String symbol, String string,
            Function<Number, String> threshold, Int integer) {
        this.symbol = symbol;
        this.string = string;
        thresh = threshold;
        Int = integer;
    }

    /**
     * compares two integers
     * 
     * @param a left
     * @param b right
     * @return whether the expression is true
     */
    public boolean compare(int a, int b) {
        return Int.compare(a, b);
    }

    /**
     * compares two objects
     * 
     * @param <T> the type of the right object
     * @param a   left
     * @param b   right
     * @return whether the expression is true
     */
    public <T> boolean compare(Comparable<T> a, T b) {
        return Int.compare(a.compareTo(b), 0);
    }

    public String equation(Number a, Number b) {
        return a.toString() + symbol + b.toString();
    }

    /**
     * @return goal to be reached
     */
    public String threshold(Number target) {
        return thresh.apply(target);
    }
}
