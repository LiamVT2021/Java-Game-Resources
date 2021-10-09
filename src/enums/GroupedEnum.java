package enums;

import util.Array;

public interface GroupedEnum<G> {

    public default boolean inGroup(G group) {
        return new Array<G>(getGroups()).has(group);
    }

    public G[] getGroups();

}
