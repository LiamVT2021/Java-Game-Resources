package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static common.pushPop.Queue.GenQueue;
import static common.prim.PrimQueue.*;
import static common.prim.NumStreamTest.assertNumEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class QueueTest {

    private static final int arrSize = 5;

    static Stream<Queue<? extends Number, Number, ?>> allQueues() {
        return Stream.of(new GenQueue<>(new Number[arrSize]),
                new ByteQueue(arrSize), new ShortQueue(arrSize), new IntQueue(arrSize),
                new LongQueue(arrSize), new FloatQueue(arrSize), new DoubleQueue(arrSize));
    }

    @ParameterizedTest
    @MethodSource("allQueues")
    public void testSwap(Queue<? extends Number, Number, ?> queue) {
        queue.fill(queue::size);
        assertEquals(0, queue.swap(5).intValue());
        assertEquals(1, queue.peek().intValue());
        assertNumEquals(queue::stream, 1, 2, 3, 4, 5);
        assertNumEquals(queue.array::stream, 5, 1, 2, 3, 4);
    }
}
