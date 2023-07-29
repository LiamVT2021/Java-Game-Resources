package common.dataStruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class StreamItTest {

    private static final List<Number> list = List.of(1, 2, 4, 8);

    private static Stream<Streamable<Number>> streams() {
        return Stream.of((Streamable<Number>) list::stream, (IterableExt<Number>) list::iterator);
    }

    @ParameterizedTest
    @MethodSource("streams")
    public void testSize(Streamable<Number> stream) {
        assertEquals(4, stream.size());
    }

    @ParameterizedTest
    @MethodSource("streams")
    public void testGetFirst(Streamable<Number> stream) {
        assertEquals(4, stream.getFirst(4));
        assertNull(stream.getFirst(n -> n.intValue() > 8));
        assertNull(stream.getFirst(null));
        assertNull(stream.getFirst((Object) null));
    }

    @ParameterizedTest
    @MethodSource("streams")
    public void testHas(Streamable<Number> stream) {
        assertTrue(stream.has(2));
        assertFalse(stream.has(null));
        assertFalse(stream.has((Object) null));
    }

    @Test
    public void testLoop() {
        Supplier<Number> loop = ((IterableExt<Number>) list::iterator).loop();
        int i = 1, s = 0;
        while (s++ < 20) {
            assertEquals(i, loop.get());
            i = i == 8 ? 1 : i * 2;
        }
        Supplier<Object> empty = ((IterableExt<Object>) List.of()::iterator).loop();
        assertNull(empty.get());
    }

}
