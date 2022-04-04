package directory;

import java.util.Stack;

public class DirViewer<E extends DirNode<E>> {

    private Stack<DirNodeView> nodeStack;
    private DirNodeView cur;
    private Directory<E> directory;

    public DirViewer(Directory<E> directory) {
        this.directory = directory;
        moveToRoot(directory);
    }

    private void moveToRoot(Directory<E> directory) {
        nodeStack = new Stack<>();
        cur = new DirNodeView(directory.root);
    }

    public DirViewer(Directory<E> directory, String path) {
        this(directory);
        for (String dir : path.split(directory.pathDelim()))
            moveChild(dir);
    }

    public String path() {
        StringBuilder path = new StringBuilder();
        for (DirNodeView node : nodeStack)
            path.append(node.node().dir() + directory.pathDelim());
        return path.toString();
    }

    public String peekParent() {
        return nodeStack.peek().node().entry();
    }

    public boolean moveParrent() {
        if (nodeStack.isEmpty())
            return false;
        cur = nodeStack.pop();
        return true;
    }

    private boolean moveChild(DirNodeView child) {
        nodeStack.push(cur);
        cur = child;
        return true;
    }

    public boolean moveChild(String dir) {
        DirNode<?> child = cur.node().children().get(dir);
        if (child == null)
            return false;
        return moveChild(new DirNodeView(cur.node(), child));
    }

    public boolean moveChild(int index) {
        if (index >= 0 && index < cur.node().children().size())
            return false;
        return moveChild(new DirNodeView(cur.node(), index));
    }

    public String title(){
        return cur.node().title();
    }

    public String page(){
        return cur.node().dirPage();
    }

}
