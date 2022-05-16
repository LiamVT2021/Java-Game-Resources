package util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;

public class NumberBuilder implements IntFunction<String> {

    protected final char[] chars;
    protected final int[] num;
    private final Map<Integer, String> cache;

    public NumberBuilder(char[] characters, int[] charValues, boolean cache) {
        chars = characters;
        num = charValues;
        this.cache = cache ? new HashMap<>() : null;
    }

    @Override
    public String apply(int value) {
        String str = cache.get(value);
        if (str != null) return str;
        StringBuilder strB = new StringBuilder();
        if (value < 0) {
            strB.append('-');
            value = 0 - value;
        }
        for (int i = 0; i < num.length; i++)
            value = indexFunc(i, value, strB);
        str = strB.toString();
        cache.put(value, str);
        return str;
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
            new int[] { 100, 50, 10, 5, 1 }, true) {
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
