package magic.domain;

import static common.prim.PrimEnumMap.FloatEnumMap;

public class Affinities extends FloatEnumMap<MagicDomain> {

    public Affinities() {
        super(MagicDomain.class);
        setAll(1);
    }

}
