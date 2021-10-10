package enums;

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
        int i = baseGet(e);
        for (G group : e.getGroups()) {
            i += groupMap.getOrDefault(group, 0);
        }
        return i;
    }

    public void set(E e, int i) {
        map.put(e, i);
    }

    public void groupSet(G group, int i) {
        groupMap.put(group, i);
    }

    public String getGroup(G group, boolean full) {
        Iterator<E> it = map.keySet().iterator();
        E first = group.first();
        while (it.next() != first)
            ;
        String str = getLine(first, full);
        int i = 1;
        while (i < group.size()) {
            E e = it.next();
            if (e.inGroup(group)) {
                str += "\n" + getLine(e, full);
                i++;
            }
        }
        return str;
    }

    private String getLine(E first, boolean full) {
        return first.name(full) + ": " + get(first);
    }
}