package magic.domain;

import static common.prim.PrimEnumMap.FloatEnumMap;

import java.util.function.ToDoubleFunction;

public enum MagicType {
    ;
    private final ToDoubleFunction<FloatEnumMap<MagicDomain>> affinityFunc;

    MagicType(ToDoubleFunction<FloatEnumMap<MagicDomain>> affinityFunc) {
        this.affinityFunc = affinityFunc;
    }

    public double affinity(FloatEnumMap<MagicDomain> affinityMap) {
        return affinityFunc.applyAsDouble(affinityMap);
    }
}
