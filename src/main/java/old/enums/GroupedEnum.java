package old.enums;

import old.util.Array;
import old.util.MultiIt;
import old.util.Named;
import old.util.SingleIt;

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
