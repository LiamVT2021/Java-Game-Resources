package enums;

import util.Array;
import util.SubArray;

public enum Affinity implements GroupedEnum<Affinity.Group> {
    ETH("Earth"), WND("Wind"), WTR("Water"), ELC("Lightning"), FIR("Fire"), CLD("Cold"), //Elemental
    LIF("Life"), DTH("Death"), HLY("Holy"), CHS("Chaos"), //Divine
    PLT("Plant"), BST("Beast"), WTH("Weather"), //Nature
    BLD("Blood"), LGT("Light"), DRK("Darkness"), FRC("Force"), MND("Mind"), VIS("Vision"), //
    SPC("Space"), TIM("Time");

    public static final Array<Affinity> arr = new Array<Affinity>(values());

    private Group type;
    private Group[] tags;
    private String fullName;

    private Affinity(String fullName, Group... tags) {
        this.tags = tags;
        for (Group tag : tags)
            ((SubArray<Affinity>) tag.members).addIndex(this.ordinal());
        this.fullName = fullName;
    }

    @Override
    public String name(boolean full) {
        return full ? fullName : name();
    }

    @Override
    public Group getType() {
        return type;
    }

    @Override
    public Group[] getTags() {
        return tags;
    }

    public enum Group implements EnumGroup<Affinity> {
        ELEMENTAL(ETH, LIF), DIVINE(LIF, PLT), NATURE(PLT, null);

        private Iterable<Affinity> members;

        private Group() {
            members = arr.subSet();
        }

        private Group(Affinity first, Affinity cut) {
            members = arr.range(first, cut);
            for (Affinity aff : members)
                aff.type = this;
        }

        @Override
        public Iterable<Affinity> getMembers() {
            return members;
        }

    }

}
