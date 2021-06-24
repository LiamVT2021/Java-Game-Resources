package body;

import java.util.Collection;

public class BodyPart {

//	private String name;
	private int maxHP;
	private Collection<Wound> wounds;
//	private int nonLethal;
//	private int lethal;
//	private int maim;
	
	
//	public BodyPart(String name, int HP) {
//		this.name = name;
//		maxHP = HP;
////		nonLethal = 0;
////		lethal = 0;
////		maim = 0;
//	}


	public int efficiency() {
		return (maxHP - getDamage()) * 100 / maxHP ;
	}

//	public String damage(int nl, int l, int m) {
//		nonLethal += nl;
//		lethal += l;
//		maim += m;
//		return null;
//	}

}
