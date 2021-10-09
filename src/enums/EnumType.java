package enums;

public interface EnumType<E extends Enum<?>> extends EnumGroup<E> {

	public int first();

	public int cutoff();

	@Override
	public default Iterable<E> enums() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public default boolean inGroup(E e) {
		return e.ordinal() >= first() && e.ordinal() < cutoff();
	}

}
