package magic.domain;

import java.util.Arrays;
import java.util.EnumSet;

public enum MagicDomain {
    EARTH("ETH"), AIR("AIR"), WATER("WTR"),
    FIRE("FIR"), COLD("CLD", Tag.NEGATIVE), ELECTRIC("ELC"),

    LIFE("LIF"), DEATH("DTH", Tag.NEGATIVE), HOLY("HLY"), CHOAS("CHS"),

    PLANT("PLT"), BEAST("BST"), WEATHER("WTH"),

    BLOOD("BLD"), LIGHT("LGT"), DARKNESS("DRK", Tag.NEGATIVE),
    FORCE("FRC"), MIND("MND"), VISION("VIS"),
    SPACE("SPC"), TIME("TIM");

    /**
     * a 3 letter name for this domain
     */
    public final String shortName;
    private final EnumSet<Tag> tags;

    MagicDomain(String shortName, Tag... tags) {
        this.shortName = shortName;
        this.tags = tags.length > 0 ? EnumSet.copyOf(Arrays.asList(tags)) : EnumSet.noneOf(Tag.class);
        for (Tag tag : tags)
            tag.addDomain(this);
    }

    public EnumSet<Tag> tags() {
        return tags.clone();
    }

    ///////////

    private static void addDomainRange(Tag tag, MagicDomain start, MagicDomain end) {
        EnumSet<MagicDomain> range = EnumSet.range(start, end);
        tag.addDomains(range);
        for (MagicDomain domain : range)
            domain.tags.add(tag);
    }

    static {
        addDomainRange(Tag.ELEMENTAL, EARTH, ELECTRIC);
        addDomainRange(Tag.DIVINE, LIFE, CHOAS);
        addDomainRange(Tag.NATURE, PLANT, WEATHER);
        Tag.finalizeDomians();
    }

}