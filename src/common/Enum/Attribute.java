package common.Enum;

public interface Attribute<E extends Enum<E>> {

    public int baseValue();

    public int mod(int value);

    public String fullName();

}
