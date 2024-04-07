package magic.domain;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public enum Tag {
    ELEMENTAL, DIVINE, NATURE,
    NEGATIVE;

    private Set<MagicDomain> domains = new HashSet<>();

    boolean addDomain(MagicDomain domain) {
        if (finalized)
            throw new RuntimeException("tag domains already finalized");
        return domains.add(domain);
    }

    boolean addDomains(Set<MagicDomain> domains) {
        if (finalized)
            throw new RuntimeException("tag domains already finalized");
        return this.domains.addAll(domains);
    }

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
