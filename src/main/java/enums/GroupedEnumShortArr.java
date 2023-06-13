// package enums;

// import java.util.Iterator;

// public abstract class GroupedEnumShortArr<E extends Enum<E> & GroupedEnum<G>, G extends EnumGroup<E>>
//         extends EnumShortArr<E> {

//     public GroupedEnumShortArr(int size, short defVal) {
//         super(size, defVal);
//     }

//     public void modGroup(G group, short mod) {
//         mod(group.getMembers(), mod);
//     }

//     public String getGroup(G group, boolean full) {
//         return getGroup(group.getMembers(), full);
//     }

//     public String getGroup(Iterable<E> keys, boolean full) {
//         Iterator<E> it = keys.iterator();
//         String str = getLine(it.next(), full);
//         while (it.hasNext()) {
//             str += "\n" + getLine(it.next(), full);
//         }
//         return str;
//     }

//     public String getLine(E e, boolean full) {
//         return e.name(full) + ": " + get(e);
//     }

// }
