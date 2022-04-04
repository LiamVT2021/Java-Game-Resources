package directory;

public class Directory<E extends DirNode<E>> {

    protected E root;

    public Directory(E root) {
        this.root = root;
    }

    public String pathDelim() {
        return "/";
    }

    public DirViewer<E> view() {
        return new DirViewer<E>(this);
    }

    public String dump() {
        return dump("--"+pathDelim(),root);
    }

    private String dump(String path, DirNode<?> node) {
        StringBuilder str = new StringBuilder(path);
        str.append(node.dump());
        path+=node.dir()+pathDelim();
        for (DirNode<?> child : node.children().values()) {
            str.append("\n\n");
            str.append(dump(path, child));
        }
        return str.toString();
    }
}
