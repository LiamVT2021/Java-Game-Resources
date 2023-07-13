package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.prim.PrimStack;

public class StackTest {

    private static final int arrSize = 5;

    private static Stream<Stack<? extends Number, Number, ?>> allStacks() {
        return Stream.of(new Stack.Gen<>(new Number[arrSize]),
                new PrimStack.Byte(arrSize), new PrimStack.Short(arrSize), new PrimStack.Int(arrSize),
                new PrimStack.Long(arrSize), new PrimStack.Float(arrSize), new PrimStack.Double(arrSize));
    }

    @ParameterizedTest
    @MethodSource("allStacks")
    public void testRev(Stack<? extends Number, Number, ?> stack) {
        assertArrayEquals(new int[] { 4, 3, 2, 1, 0 },
                stack.fill(stack::size).empty().mapToInt(n -> n.intValue()).toArray());
    }

    @ParameterizedTest
    @MethodSource("allStacks")
    public void testEdge(Stack<? extends Number, Number, ?> stack) {
        assertFalse(stack.push(null));
        assertNull(stack.peek());
        assertNull(stack.pop());
        assertEquals(5, stack.swap(5).intValue());
        stack.fill(stack::size);
        assertFalse(stack.push(5));
        assertEquals(4, stack.swap(5).intValue());
        assertEquals(5, stack.peek().intValue());
    }

    @Test
    public void testToString() {
        Stack.Gen<Number> stack = new Stack.Gen<>(new Number[arrSize]);
        assertEquals("Peek: 4\n 5 / 5\n" + //
                "[ 0, 1, 2, 3, 4 ]\n" + //
                "< 4, 3, 2, 1, 0 >", stack.fill(stack::size).toString());
    }

}
