package directory;

public interface DirNodeADT<E extends DirNodeADT<?>> {

    String dir();

    String entry();

    String title();

    String dirPage();

    boolean isLeaf();
    
    boolean isEmpty();

    boolean addChild(E child);

    void removeChild(String dir);

    boolean prune();


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
