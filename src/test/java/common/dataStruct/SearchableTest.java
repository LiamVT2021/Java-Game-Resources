package common.dataStruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import common.prim.array.IntArray;

public class SearchableTest {

    private static final IntArray arr = new IntArray(1, 2, 4, 8);

    private static Stream<Searchable<Integer>> streams() {
        return Stream.of((IterableExt<Integer>) arr::iterator, (Streamable<Integer>) arr::stream);
    }

    // @Test
    // public void testSize() {
    //     assertEquals(4, struct.size());
    // }

    @ParameterizedTest
    @MethodSource("streams")
    public void testGetFirst(Searchable<Integer> stream) {
        assertEquals(4, stream.getFirst(4));
        assertNull(stream.getFirst(n -> n.intValue() > 8));
        assertNull(stream.getFirst(null));
        assertNull(stream.getFirst((Object) null));
    }

    @ParameterizedTest
    @MethodSource("streams")
    public void testHas(Searchable<Integer> stream) {
        assertTrue(stream.has(2));
        assertFalse(stream.has(null));
        assertFalse(stream.has((Object) null));
    }

    // @Test
    // public void testLoop() {
    //     Supplier<Number> loop = struct.loop();
    //     int i = 1, s = 0;
    //     while (s++ < 20) {
    //         assertEquals(i, loop.get());
    //         i = i == 8 ? 1 : i * 2;
    //     }
    //     Supplier<Number> empty = new Struct().loop();
    //     assertNull(empty.get());
    // }

}
