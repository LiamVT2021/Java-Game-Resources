package common.pushPop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class PushPopTest {

    public void testPushPop(PushPopArray<? extends Number, Number, ?> pushPop) {
        assertFalse(pushPop.pushAll(null, 4));
        assertFalse(pushPop.push(null));
        assertNull(pushPop.peek());
        assertNull(pushPop.pop());
        assertEquals(5, pushPop.swap(5).intValue());
        pushPop.fill(() -> 5);
        assertFalse(pushPop.push(3));
        assertFalse(pushPop.pushAll(4, 5));
        int s = pushPop.size();
        assertEquals(5, pushPop.swap(null).intValue());
        assertEquals(s - 1, pushPop.size());
    }

}
