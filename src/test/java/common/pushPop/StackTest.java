package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static common.prim.NumStreamTest.assertNumEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.pushPop.Stack.GenStack;
import common.prim.PrimStack.*;

public class StackTest extends PushPopTest {

    private static final int arrSize = 5;

    static Stream<Stack<? extends Number, Number, ?>> allStacks() {
        return Stream.of(new GenStack<>(new Number[arrSize]),
                new ByteStack(arrSize), new ShortStack(arrSize), new IntStack(arrSize),
                new LongStack(arrSize), new FloatStack(arrSize), new DoubleStack(arrSize));
    }

    @ParameterizedTest
    @MethodSource("allStacks")
    public void testPushPop(PushPopArray<? extends Number, Number, ?> pushPop) {
        super.testPushPop(pushPop);
    }

    @ParameterizedTest
    @MethodSource("allStacks")
    public void testRev(Stack<? extends Number, Number, ?> stack) {
        assertNumEquals(stack.fill(stack::size)::empty, 4, 3, 2, 1, 0);
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
