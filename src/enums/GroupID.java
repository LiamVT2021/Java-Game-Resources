package enums;

public interface GroupID<E extends Enum<?>> {

	Iterable<E> enums();

	boolean inGroup(E e);

}
