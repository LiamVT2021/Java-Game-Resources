package magic.domain;

import java.util.Arrays;
import java.util.EnumSet;

public enum MagicDomain {
    EARTH("ETH", Tag.ELEMENTAL), AIR("AIR", Tag.ELEMENTAL), WATER("WTR", Tag.ELEMENTAL),
    FIRE("FIR", Tag.ELEMENTAL), COLD("CLD", Tag.ELEMENTAL), ELECTRIC("ELC", Tag.ELEMENTAL),

    LIFE("LIF", Tag.DIVINE), DEATH("DTH", Tag.DIVINE), HOLY("HLY", Tag.DIVINE), CHOAS("CHS", Tag.DIVINE),

    PLANT("PLT", Tag.NATURE), BEAST("BST", Tag.NATURE), WEATHER("WTH", Tag.NATURE),

    BLOOD("BLD"), LIGHT("LGT"), DARKNESS("DRK"), FORCE("FRC"), MIND("MND"), VISION("VIS"),
    SPACE("SPC"), TIME("TIM");

    private final String str;
    private EnumSet<Tag> tags;

    MagicDomain(String shortName, Tag... tags) {
        str = shortName;
        this.tags = tags.length > 0 ? EnumSet.copyOf(Arrays.asList(tags)) : EnumSet.noneOf(Tag.class);
        for (Tag tag : tags)
            tag.domains.add(this);
    }

    static {
        Tag.finalizeDomians();
    }

    /**
     * @return a 3 letter name for this domain
     */
    public String shortName() {
        return str;
    }

    public EnumSet<Tag> tags() {
        return tags.clone();
    }

}