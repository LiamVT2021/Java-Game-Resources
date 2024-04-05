package magic;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagicDomainTest {

    @ParameterizedTest
    @EnumSource(MagicDomain.class)
    public void testShortNameLength(MagicDomain domain) {
        assertEquals(3, domain.shortName().length());
    }

    @Test
    public void testShortNameUnique() {
        assertEquals(MagicDomain.values().length,
                Stream.of(MagicDomain.values()).map(MagicDomain::shortName).distinct().count());
    }

}
