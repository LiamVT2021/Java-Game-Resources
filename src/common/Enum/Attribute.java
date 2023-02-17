package common.Enum;

public interface Attribute<E extends Enum<E>> {

    public int baseValue();

    public int mod(Number value);

    public String fullName();

}
