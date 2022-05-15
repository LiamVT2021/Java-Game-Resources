package util;

import java.util.function.IntFunction;

public class NumberBuilder implements IntFunction<String> {

    protected final char[] chars;
    protected final int[] num;

    public NumberBuilder(char[] characters, int[] charValues) {
        chars = characters;
        num = charValues;
    }

    @Override
    public String apply(int value) {
        StringBuilder strB = new StringBuilder();
        if (value < 0) {
            strB.append('-');
            value = 0 - value;
        }
        for (int i = 0; i < num.length; i++)
            value = indexFunc(i, value, strB);
        return strB.toString();
    }

    protected int indexFunc(int index, int value, StringBuilder strB) {
        int v = num[index];
        char c = chars[index];
        while (value >= v) {
            strB.append(c);
            value -= v;
        }
        return value;
    }

    public static final NumberBuilder ROMAN = new NumberBuilder(new char[] { 'C', 'L', 'X', 'V', 'I' },
            new int[] { 100, 50, 10, 5, 1 }) {
        @Override
        protected int indexFunc(int index, int value, StringBuilder strB) {
            value = super.indexFunc(index, value, strB);
            int n = num[index];
            int hn = n / 2;
            for (int j = num.length - 1; j > index; j--) {
                int v = n - num[j];
                if (v != hn / 2 && value >= v) {
                    strB.append(chars[j]);
                    strB.append(chars[index]);
                    return value - v;
                }
            }
            return value;
        }
    };

}
