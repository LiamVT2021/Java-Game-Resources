package enums;

import static enums.Attribute.Type.MENTAL;
import static enums.Attribute.Type.PHYSICAL;

public enum Attribute implements GroupedEnum<Attribute.Type> {

    STR("Strength", PHYSICAL), CON("Constitution", PHYSICAL), DEX("Dexterity", PHYSICAL), AGI("Agility", PHYSICAL),
    APL("Appeal", PHYSICAL), //
    INT("Intelegence", MENTAL), WIS("Widsom", MENTAL), PER("Perception", MENTAL), CHA("Charisma", MENTAL),
    WIL("Will", MENTAL);

    private Type[] tags;
    private String fullName;

    private Attribute(String fullName, Type... tags) {
        this.fullName = fullName;
        this.tags = tags;
        for (Type tag : tags)
            tag.id.put(this);
    }

    @Override
    public Type[] getGroups() {
        return tags;
    }

    @Override
    public String name(boolean full) {
        return full ? fullName : name();
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
