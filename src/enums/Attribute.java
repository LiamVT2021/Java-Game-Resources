package enums;

public enum Attribute implements GroupedEnum<Attribute> {

	STR, DEX, //
	INT, WIS;

	@Override
	public EnumGroup<Attribute>[] allTypes() {
		return Type.values();
	}

	@Override
	public EnumGroup<Attribute>[] allTags() {
		// TODO Auto-generated method stub
		return null;
	}

	public enum Type implements EnumType<Attribute> {
		Physical(STR), Mental(INT);

		private Attribute first;

		private Type(Attribute first) {
			this.first = first;
		}

		@Override
		public int first() {
			return first.ordinal();
		}

		@Override
		public int cutoff() {
			return (Type.values()[ordinal() + 1]).first.ordinal();
		}

	}

}
