package enums;

public interface EnumGroup<E extends Enum<?>> {// , G extends GroupID<E>> {

	Iterable<E> enums();

	boolean inGroup(E e);

}
