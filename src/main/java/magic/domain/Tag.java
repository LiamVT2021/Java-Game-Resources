package magic.domain;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public enum Tag {
    ELEMENTAL, DIVINE, NATURE;

    Set<MagicDomain> domains = new HashSet<>();

    public EnumSet<MagicDomain> domains() {
        return EnumSet.copyOf(domains);
    }

    static boolean finalized = false;

    static void finalizeDomians() {
        if (finalized)
            throw new RuntimeException("tag domains already finalized");
        for (Tag tag : values())
            tag.domains = tag.domains();
        finalized = true;
    }

}
