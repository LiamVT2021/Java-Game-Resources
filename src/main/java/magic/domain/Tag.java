package magic.domain;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public enum Tag {
    ELEMENTAL, DIVINE, NATURE;

    Set<MagicDomain> domains = new HashSet<>();

    static void finalizeDomians() {
        for (Tag tag : values())
            tag.domains = tag.domains();
    }

    public EnumSet<MagicDomain> domains() {
        return EnumSet.copyOf(domains);
    }

}
