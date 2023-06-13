package enums;

import util.Array;
import util.MultiIt;
import util.Named;
import util.SingleIt;

public interface GroupedEnum<G> extends Named {

    public default boolean inGroup(G group) {
        if (getType() == group)
            return true;
        return new Array<G>(getTags()).has(group);
    }

    public default Iterable<G> getGroups() {
        return new MultiIt<G>(() -> new SingleIt<G>(getType()), new Array<G>(getTags()));
    }

    public G getType();

    public G[] getTags();

}
