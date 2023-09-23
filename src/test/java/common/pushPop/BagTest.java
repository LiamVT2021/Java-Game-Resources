package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static common.prim.NumStreamTest.assertNumEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.pushPop.Bag.GenBag;
import common.prim.PrimBag.*;

public class BagTest extends PushPopTest {

    private static final int arrSize = 5;

    static Stream<Bag<? extends Number, Number, ?>> allBags() {
        return Stream.of(new GenBag<>(new Number[arrSize]),
                new ByteBag(arrSize), new ShortBag(arrSize), new IntBag(arrSize),
                new LongBag(arrSize), new FloatBag(arrSize), new DoubleBag(arrSize));
    }

    private void fill(Bag<? extends Number, Number, ?> bag) {
        bag.fill(bag::size);
    }

    @ParameterizedTest
    @MethodSource("allBags")
    public void testPushPop(PushPopArray<? extends Number, Number, ?> pushPop) {
        super.testPushPop(pushPop);
    }

    @ParameterizedTest
    @MethodSource("allBags")
    public void testFill(Bag<? extends Number, Number, ?> bag) {
        fill(bag);
        assertNumEquals(bag, 0, 1, 2, 3, 4);
    }

    @Test
    public void testIndex() {
        IntBag bag = new IntBag(arrSize) {
            @Override
            protected int peekIndex() {
                return size == 1 ? super.peekIndex() : 2;
            }
        };
        bag.push(8);
        assertEquals(8, bag.peek());
        fill(bag);
        assertEquals(2, bag.swap(6));
        assertEquals(6, bag.pop());
        assertEquals(4, bag.peek());
    }

}
