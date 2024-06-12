package magic.domain;

import java.awt.Color;
import java.util.Arrays;
import java.util.EnumSet;

import common.ui.Colors;

public enum MagicDomain {
    EARTH("ETH", Colors.BROWN), AIR("AIR", Color.WHITE), WATER("WTR", Color.BLUE),
    FIRE("FIR", Color.ORANGE), COLD("CLD", null, Tag.NEGATIVE), ELECTRIC("ELC", null),

    LIFE("LIF", null), DEATH("DTH", Color.DARK_GRAY, Tag.NEGATIVE), HOLY("HLY", null), CHOAS("CHS", null),

    PLANT("PLT", Color.GREEN), BEAST("BST", null), WEATHER("WTH", null),

    // BLOOD("BLD", Color.RED), LIGHT("LGT"), DARKNESS("DRK", Color.BLACK, Tag.NEGATIVE),
    // FORCE("FRC"), MIND("MND"), VISION("VIS"),
    // SPACE("SPC"), TIME("TIM"),
    ;

    /**
     * a 3 letter name for this domain
     */
    public final String shortName;
    public final Color color;
    private final EnumSet<Tag> tags;

    MagicDomain(String shortName , Color color, Tag... tags) {
        this.shortName = shortName;
        this.color = color; 
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