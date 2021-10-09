package enums;

public interface GroupedEnum<E extends Enum<?>> {

	EnumGroup<E>[] allTypes();

	EnumGroup<E>[] allTags();

	@SuppressWarnings("unchecked")
	default EnumGroup<E> getType() {
		for (EnumGroup<E> type : allTypes()) {
			if (type.inGroup((E) this))
				return type;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	default boolean inGroup(EnumGroup<E> group) {
		return group.inGroup((E) this);
	}

	default Iterable<EnumGroup<E>> getGroups() {
		return null;
	}

}
