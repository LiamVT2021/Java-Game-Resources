package old.enums;

import java.util.EnumMap;
import java.util.Iterator;

public class GroupedEnumMap<E extends Enum<E> & GroupedEnum<G>, G extends Enum<G> & EnumGroup<E>> {

    private EnumMap<E, Integer> map;
    private EnumMap<G, Integer> groupMap;

    public GroupedEnumMap(Class<E> eClass, Class<G> gClass) {
        map = new EnumMap<E, Integer>(eClass);
        groupMap = new EnumMap<G, Integer>(gClass);
    }

    public int baseGet(E e) {
        return map.getOrDefault(e, 0);
    }

    public int get(E e) {
        int i = baseGet(e) + groupGet(e.getType());
        for (G group : e.getTags()) {
            i += groupGet(group);
        }
        return i;
    }

    private Integer groupGet(G group) {
        return groupMap.getOrDefault(group, 0);
    }

    public void set(E e, int i) {
        map.put(e, i);
    }

    public void groupSet(G group, int i) {
        groupMap.put(group, i);
    }

    public String getGroup(G group, boolean full) {
        Iterator<E> it = group.getMembers().iterator();
        String str = getLine(it.next(), full);
        while (it.hasNext()) {
                str += "\n" + getLine(it.next(), full);
        }
        return str;
    }

    private String getLine(E e, boolean full) {
        return e.name(full) + ": " + get(e);
    }
}