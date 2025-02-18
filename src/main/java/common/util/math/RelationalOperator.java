package common.util.math;

/**
 * Compares two numerical values
 * 
 * @version 2/17/25
 */
public enum RelationalOperator {

    EQ("=", "equal to", "is", (a, b) -> a == b),
    NE("!=", "not equal to", "not", (a, b) -> a != b),
    LT("<", "less than", "less than", (a, b) -> a < b),
    LE("<=", "less than or equal to", "at most", (a, b) -> a <= b),
    GT(">", "greater than", "more than", (a, b) -> a > b),
    GE(">=", "greater than or equal to", "at least", (a, b) -> a >= b);

    public final String symbol, math, simple;
    private final Int Int;

    private interface Int {
        boolean compare(int a, int b);
    }

    private RelationalOperator(String symbol, String math, String simple, Int integer) {
        this.symbol = symbol;
        this.math = math;
        this.simple = simple;
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

}
