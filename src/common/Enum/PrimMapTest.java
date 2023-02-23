package common.Enum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class PrimMapTest {

    private enum Type {
        A, B, C, D;
    }

    private PrimMap.Byte<Type> byteMap;
    private PrimMap.Int<Type> intMap;

    @BeforeEach
    public void setUp() {
        byteMap = new PrimMap.Byte<>(Type.class);
        intMap = new PrimMap.Int<>(Type.class);
    }

    private void forEach(Consumer<PrimMap<Type, ?, ?>> func) {
        Stream.of(byteMap, intMap).forEach(func);
    }

    @Test
    public void testEnumMethods() {
        forEach(map -> {
            assertFalse(map.isEmpty());
            assertEquals(4, map.size());
            assertTrue(map.containsKey(Type.A));
            assertFalse(map.containsKey("A"));
            // assertTrue(Type.values() == map.keys());
        });
    }

}
