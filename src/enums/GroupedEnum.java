package enums;

import util.Array;
import util.Named;

public interface GroupedEnum<G> extends Named {

    public default boolean inGroup(G group) {
        return new Array<G>(getGroups()).has(group);
    }

    public G[] getGroups();

}
