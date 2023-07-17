package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PushPopTest {

    private static Stream<PushPopArray<? extends Number, Number, ?>> allPushPops() {
        ArrayList<PushPopArray<? extends Number, Number, ?>> pushPops = new ArrayList<>();
        StackTest.allStacks().forEach(pushPops::add);
        // QueueTest.allQueues().forEach(pushPops::add);
        BagTest.allBags().forEach(pushPops::add);
        HeapTest.minHeaps().forEach(pushPops::add);
        HeapTest.maxHeaps().forEach(pushPops::add);
        return pushPops.stream();
    }

    @ParameterizedTest
    @MethodSource("allPushPops")
    public void testEdge(PushPopArray<? extends Number, Number, ?> pushPop) {
        // assertFalse(pushPop.pushAll(null, 4));
        assertFalse(pushPop.push(null));
        assertNull(pushPop.peek());
        assertNull(pushPop.pop());
        assertEquals(5, pushPop.swap(5).intValue());
        pushPop.fill(() -> 5);
        assertFalse(pushPop.push(3));
        // assertFalse(pushPop.pushAll(4, 5));
        int s = pushPop.size();
        assertEquals(5, pushPop.swap(null).intValue());
        assertEquals(s - 1, pushPop.size());
    }

}
