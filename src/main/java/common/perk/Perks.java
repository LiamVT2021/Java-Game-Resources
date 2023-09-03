package common.perk;

import java.util.EnumSet;
import java.util.Set;

import common.prim.PrimEnumMap.ByteEnumMap;

public class Perks<S extends Enum<S>, L extends Enum<L>> {

    // use large if range is greater than 8

    private final EnumSet<S> small;
    private final ByteEnumMap<L> large;

    public Perks(Class<S> small, Class<L> large) {
        this.small = EnumSet.noneOf(small);
        this.large = new ByteEnumMap<>(large);
    }

    public boolean hasPerk(S perk) {
        return small.contains(perk);
    }

    public byte getLevel(Set<S> perks) {
        Set<S> inter = EnumSet.copyOf(perks);
        inter.retainAll(small);
        return (byte) inter.size();
    }

    public byte getLevel(L perk) {
        return large.get(perk);
    }

    //

    public void givePerk(S perk) {
        small.add(perk);
    }

    public void removePerk(S perk) {
        small.remove(perk);
    }

    public void setLevel(L perk, byte level) {
        large.set(perk, level);
    }

}
