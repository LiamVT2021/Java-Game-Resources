package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static common.pushPop.Queue.GenQueue;
import static common.prim.PrimQueue.*;

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

    // @ParameterizedTest
    // @MethodSource("allQueues")
    // public void testQueue(Queue<? extends Number, Number, ?> queue) {
    //     assertNull(queue.peek());
    //     assertNull(queue.pop());
    //     assertNull(queue.swap(null));
    //     assertNull(queue.swap(arrSize));
    //     // bag.push(8);
    //     // assertEquals(8, bag.peek());
    //     // fill(bag);
    //     // assertEquals(2, bag.swap(6));
    //     // assertEquals(6, bag.pop());
    //     // assertEquals(4, bag.peek());
    // }
}
