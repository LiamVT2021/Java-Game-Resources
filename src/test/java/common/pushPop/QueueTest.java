package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static common.prim.NumStreamTest.assertNumEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.pushPop.Queue.GenQueue;
import common.prim.PrimQueue.*;

public class QueueTest extends PushPopTest {

    private static final int arrSize = 5;

    static Stream<Queue<? extends Number, Number, ?>> allQueues() {
        return Stream.of(new GenQueue<>(new Number[arrSize]),
                new ByteQueue(arrSize), new ShortQueue(arrSize), new IntQueue(arrSize),
                new LongQueue(arrSize), new FloatQueue(arrSize), new DoubleQueue(arrSize));
    }

    @ParameterizedTest
    @MethodSource("allQueues")
    public void testPushPop(PushPopArray<? extends Number, Number, ?> pushPop) {
        super.testPushPop(pushPop);
    }

    @ParameterizedTest
    @MethodSource("allQueues")
    public void testSwap(Queue<? extends Number, Number, ?> queue) {
        queue.fill(queue::size);
        assertEquals(0, queue.swap(5).intValue());
        assertEquals(1, queue.peek().intValue());
        assertNumEquals(queue, 1, 2, 3, 4, 5);
        assertNumEquals(queue.array, 5, 1, 2, 3, 4);
    }
}
