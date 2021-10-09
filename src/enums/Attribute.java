package enums;

import static enums.Attribute.Type.*;

public enum Attribute implements GroupedEnum<Attribute.Type> {

    STR(PHYSICAL), CON(PHYSICAL), DEX(PHYSICAL), AGI(PHYSICAL), //
    INT(MENTAL), WIS(MENTAL), CHA(MENTAL), WIL(MENTAL);

    private Type[] tags;

    private Attribute(Type... tags) {
        this.tags = tags;
        for (Type tag : tags)
            tag.id.put(this);
    }

    @Override
    public Type[] getGroups() {
        return tags;
    }

    public enum Type implements EnumGroup<Attribute> {
        PHYSICAL, MENTAL;

        private GroupID<Attribute> id;

        private Type() {
            id = new GroupID<Attribute>();
        }

        @Override
        public GroupID<Attribute> id() {
            return id;
        }

    }

}
