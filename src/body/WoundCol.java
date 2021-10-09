package body;

public interface WoundCol {

	public Iterable<Wound> wounds(boolean all);

//	public Iterable<Wound> allWounds();

//	public default int getDamage(boolean all) {
//		int dmg = 0;
//		for (Wound wound : wounds(all))
//			dmg += wound.getDamage();
//		return dmg;
//	}
//
//	public default int[] getTotals(boolean all) {
//		int[] arr = new int[5];
//		float treat = 0;
//		for (Wound wound : wounds(all)) {
//			arr[0] += wound.nonLethal();
//			arr[1] += wound.lethal();
//			arr[2] += wound.maim();
//			arr[3] += wound.getBleed();
//			if (!wound.isScar()) {
//				arr[4]++;
//				treat += wound.getTreat();
//			}
//		}
//		if (arr[4] != 0)
//			arr[4] = (int) (100 * treat / arr[4]);
//		return arr;
//	}

//	@Override
//	public default int nonLethal() {
//		int nl = 0;
//		for (Wounded wound : wounds()) {
//			nl += wound.nonLethal();
//		}
//		return nl;
//	}
//
//	@Override
//	public default int lethal() {
//		int l = 0;
//		for (Wounded wound : wounds()) {
//			l += wound.lethal();
//		}
//		return l;
//	}
//
//	@Override
//	public default int maim() {
//		int m = 0;
//		for (Wounded wound : wounds()) {
//			m += wound.maim();
//		}
//		return m;
//	}
//
//	@Override
//	public default int getDamage() {
//		int damage = 0;
//		for (Wounded wound : wounds()) {
//			damage += wound.getDamage();
//		}
//		return damage;
//	}
//
//	@Override
//	public default int getBleed() {
//		int bleed = 0;
//		for (Wounded wound : wounds()) {
//			bleed += wound.getBleed();
//		}
//		return bleed;
//	}
//
//	@Override
//	public default float getTreat() {
//		int count = 0;
//		float treat = 0;
//		for (Wounded wound : wounds()) {
//			treat += wound.getTreat();
//			count++;
//		}
//		return treat / count;
//	}

//	public default String addIcon(String str, boolean all) {
//		int bleed = 0;
//		int count = 0;
//		float treat = 0;
//		for (Wound wound : wounds(all)) {
//			bleed += wound.getBleed();
//			treat += wound.getTreat();
//			count++;
//		}
//		treat /= count;
//		if (bleed > 0)
//			str += " *" + bleed;
//		if (treat > 0)
//			str += " x" + (int) (treat * 100);
//		return str;
//	}

	public default String totalString(boolean all) {
		int[] arr = getTotals(all);
		String str = name + ": " + arr[0] + "|" + arr[1] + "|"
				+ arr[2];
		if (arr[3] > 0)
			str += " *" + arr[3];
		if (arr[4] > 0)
			str += " x" + arr[4];
		return str;

	}

}
