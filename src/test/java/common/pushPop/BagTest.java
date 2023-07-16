package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import static common.pushPop.Bag.GenBag;
import static common.prim.PrimBag.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BagTest {

    private static final int arrSize = 5;

    private static Stream<Bag<? extends Number, Number, ?>> allBags() {
        return Stream.of(new GenBag<>(new Number[arrSize]),
                new ByteBag(arrSize), new ShortBag(arrSize), new IntBag(arrSize),
                new LongBag(arrSize), new FloatBag(arrSize), new DoubleBag(arrSize));
    }

    private void fill(Bag<? extends Number, Number, ?> bag) {
        bag.fill(bag::size);
    }

    @ParameterizedTest
    @MethodSource("allBags")
    public void testPush(Bag<? extends Number, Number, ?> bag) {
        assertFalse(bag.push(null));
        fill(bag);
        assertFalse(bag.push(arrSize));
        assertArrayEquals(new int[] { 0, 1, 2, 3, 4 }, bag.stream().mapToInt(Number::intValue).toArray());
    }

    @Test
    public void testDraw() {
        IntBag bag = new IntBag(arrSize) {
            @Override
            protected int draw() {
                return 2;
            }
        };
        assertNull(bag.peek());
        assertNull(bag.pop());
        assertNull(bag.swap(null));
        assertNull(bag.swap(arrSize));
        fill(bag);
        assertEquals(2, bag.swap(6));
        assertEquals(6, bag.pop());
        assertEquals(4, bag.peek());
    }

}
