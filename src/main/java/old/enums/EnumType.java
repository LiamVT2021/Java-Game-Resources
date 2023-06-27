package old.enums;

public class EnumType<E> {

    private Iterable<E> members;

    public EnumType(Iterable<E> members) {
        this.members = members;
    }

    public Iterable<E> getMembers() {
        return members;
    }
}
