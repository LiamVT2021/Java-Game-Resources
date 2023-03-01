package common.game;

import java.util.function.BiConsumer;

import common.Builders;
import common.Enum.Attribute;

public interface Sheet<A extends Enum<A> & Attribute<A>> {

    public int getAttribute(A attribute);

    public default int getAttMod(A attribute) {
        return attribute.mod(getAttribute(attribute));
    }

    public void forEachAttribute(BiConsumer<? super A, Integer> func);

    public default String attTable(boolean fullName) {
        return Builders.buildString(str -> forEachAttribute((att, val) -> str.append(
                modString(fullName ? att.fullName() : att.toString(), att.mod(val)) + '(' + val + ')')));
    }

    public static String modString(String key, Number value) {
        return key + (value.intValue() > 0 ? ": +" : ": ") + value;
    }

}
