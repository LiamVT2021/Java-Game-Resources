package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.prim.PrimStack;

public class StackTest {

    private Stack.Gen<Number> numStack;
    private PrimStack.Byte byteStack;
    private PrimStack.Short shortStack;
    private PrimStack.Int intStack;
    private PrimStack.Long longStack;
    private PrimStack.Float floatStack;
    private PrimStack.Double doubleStack;

    private static final int arrSize = 5;

    @BeforeEach
    private void setUp() {
        numStack = new Stack.Gen<>(new Number[arrSize]);
        byteStack = new PrimStack.Byte(arrSize);
        shortStack = new PrimStack.Short(arrSize);
        intStack = new PrimStack.Int(arrSize);
        longStack = new PrimStack.Long(arrSize);
        floatStack = new PrimStack.Float(arrSize);
        doubleStack = new PrimStack.Double(arrSize);
    }

    private void forAll(Consumer<Stack<? extends Number, Number, ?>> consumer) {
        Stream.of(numStack, byteStack, shortStack, intStack, longStack, floatStack, doubleStack).forEach(consumer);
    }

    private void fill() {
        forAll(stack -> stack.fill(stack::size));
    }

    @Test
    public void testRev() {
        fill();
        assertEquals("[ 0, 1, 2, 3, 4 ]", numStack.arrayString());
        forAll(stack -> assertArrayEquals(new int[] { 4, 3, 2, 1, 0 },
                stack.empty().stream().mapToInt(n -> n.intValue()).toArray()));
    }

    @Test
    public void testEdge() {
        forAll(stack -> {
            assertFalse(stack.push(null));
            assertNull(stack.peek());
            assertNull(stack.pop());
            assertEquals(5, stack.swap(5).intValue());
        });
        fill();
        forAll(stack -> {
            assertFalse(stack.push(5));
            assertEquals(4, stack.swap(5).intValue());
            assertEquals(5, stack.peek().intValue());
        });
    }

}
