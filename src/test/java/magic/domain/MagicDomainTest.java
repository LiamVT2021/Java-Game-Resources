package magic.domain;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @ParameterizedTest
    @EnumSource(Tag.class)
    public void testHasTag(Tag tag) {
        MagicDomain.values();
        Set<MagicDomain> domains = tag.domains();
        assertTrue(domains.size() > 0);
        for (MagicDomain domain : domains)
            assertTrue(domain.tags().contains(tag));
    }

}
