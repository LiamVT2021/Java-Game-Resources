package util;

import java.util.Arrays;

public class FillString implements CharSequence {

    private final char ch;
    private final int len;

    public FillString(char ch, int len) {
        this.ch = ch;
        this.len = len;
    }

    @Override
    public int length() {
        return len;
    }

    private boolean outBounds(int index) {
        return index < 0 || index >= len;
    }

    @Override
    public char charAt(int index) {
        if (outBounds(index))
            throw new IndexOutOfBoundsException();
        return ch;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start > end || outBounds(start) || outBounds(end))
            throw new IndexOutOfBoundsException();
        return new FillString(ch, end - start);
    }

    @Override
    public String toString() {
        char[] arr = new char[len];
        Arrays.fill(arr, ch);
        return new String(arr);
    }

}
