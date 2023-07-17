package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static common.pushPop.Stack.GenStack;
import static common.prim.PrimStack.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class StackTest {

    private static final int arrSize = 5;

    static Stream<Stack<? extends Number, Number, ?>> allStacks() {
        return Stream.of(new GenStack<>(new Number[arrSize]),
                new ByteStack(arrSize), new ShortStack(arrSize), new IntStack(arrSize),
                new LongStack(arrSize), new FloatStack(arrSize), new DoubleStack(arrSize));
    }

    @ParameterizedTest
    @MethodSource("allStacks")
    public void testRev(Stack<? extends Number, Number, ?> stack) {
        assertArrayEquals(new int[] { 4, 3, 2, 1, 0 },
                stack.fill(stack::size).empty().mapToInt(n -> n.intValue()).toArray());
    }

    @ParameterizedTest
    @MethodSource("allStacks")
    public void testSwap(Stack<? extends Number, Number, ?> stack) {
        stack.fill(stack::size);
        assertEquals(4, stack.swap(5).intValue());
        assertEquals(5, stack.peek().intValue());
    }

    @Test
    public void testToString() {
        IntStack stack = new IntStack(arrSize);
        assertEquals("IntStack: 5 / 5\n< 4, 3, 2, 1, 0 >", stack.fill(stack::size).toString());
        assertEquals("[ 0, 1, 2, 3, 4 ]", stack.arrayString());
    }

}
