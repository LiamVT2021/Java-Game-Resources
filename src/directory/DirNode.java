package directory;

import java.util.Map;

public interface DirNode<E extends DirNode<?>> {

    String dir();

    String entry();

    String title();

    String dirPage();

    Map<String, E> children();

    default boolean isLeaf() {
        return children().isEmpty();
    }

    default boolean put(E child) {
        children().put(child.dir(), child);
        return true;
    }

    default String dump() {
        StringBuilder str = new StringBuilder(dir());
        str.append("\nEntry: ");
        str.append(entry());
        str.append("\nTitle: ");
        str.append(title());
        str.append("\n");
        str.append(dirPage());
        return str.toString();
    }

}
