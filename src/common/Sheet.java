package common;

import java.util.function.BiConsumer;

import common.Enum.Attribute;

public interface Sheet<A extends Enum<A> & Attribute<A>> {

    public int getAttribute(A attribute);

    public default int getAttMod(A attribute) {
        return attribute.mod(getAttribute(attribute));
    }

    public void forEachAttribute(BiConsumer<? super A, Number> func);

    public default String attTable(boolean fullName) {
        return Builders.buildString(str -> forEachAttribute((att, val) -> str.append(
                (fullName ? att.fullName() : att.toString())
                        + (val.intValue() > 0 ? ": +" : ": ")
                        + att.mod(val) + '(' + val + ')')));
    }

}
