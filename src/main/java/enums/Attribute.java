package enums;

import util.Array;

public enum Attribute implements GroupedEnum<Attribute.Type> {

    STR("Strength"), CON("Constitution"), DEX("Dexterity"), AGI("Agility"), APL("Appeal"), //
    INT("Intelegence"), WIS("Widsom"), PER("Perception"), CHA("Charisma"), WIL("Will");

    public static final Array<Attribute> arr = new Array<Attribute>(values());
    public static final short defVal = 20;

    private Type type;
    // private Type[] tags;
    private String fullName;

    private Attribute(String fullName, Type... tags) {
        this.fullName = fullName;
        // this.tags = tags;
        // for (Type tag : tags)
        // tag.id.put(this);
    }

    @Override
    public String name(boolean full) {
        return full ? fullName : name();
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Type[] getTags() {
        return new Type[0];
    }

    public enum Type implements EnumGroup<Attribute> {
        PHYSICAL(STR, INT), MENTAL(INT, null);

        private Iterable<Attribute> members;

        // private Type(Attribute... atts){
        // // this(STR, INT);
        // System.out.println("bad");
        // }

        private Type(Attribute first, Attribute cut) {
            members = arr.range(first, cut);
            for (Attribute att : members)
                att.type = this;
        }

        @Override
        public Iterable<Attribute> getMembers() {
            return members;
        }

    }

}
