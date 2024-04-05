package magic;

public enum MagicDomain {
    EARTH("ETH"), AIR("AIR"), WATER("WTR"), FIRE("FIR"), COLD("CLD"), ELECTRIC("ELC"), // Elemental
    LIFE("LIF"), DEATH("DTH"), HOLY("HLY"), CHOAS("CHS"), // Divine
    PLANT("PLT"), BEAST("BST"), WEATHER("WTH"),
    BLOOD("BLD"), LIGHT("LGT"), DARKNESS("DRK"), FORCE("FRC"), MIND("MND"), VISION("VIS"),
    SPACE("SPC"), TIME("TIM");

    private final String str;

    MagicDomain(String shortName) {
        str = shortName;
    }

    /**
     * @return a 3 letter name for this domain
     */
    public String shortName() {
        return str;
    }
}