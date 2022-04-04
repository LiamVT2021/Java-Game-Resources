package directory;

import java.util.Map;

public abstract class DirNode<E extends DirNodeADT<?>> implements DirNodeADT<E> {

    protected Map<String, E> children;

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public boolean addChild(E child) {
        if (!canAdd(child))
            return false;
        children.put(child.dir(), child);
        return true;
    }

    private boolean canAdd(DirNodeADT<?> node) {
        if (node == this)
            return false;
        if (node.isLeaf())
            return true;
        return ((DirNode<?>) node).children.values().stream().allMatch(this::canAdd);
    }

    @Override
    public void removeChild(String dir) {
        children.remove(dir);
    }

    @Override
    public boolean prune() {
        children.values().stream().filter(n -> n.prune()).map(n -> n.dir()).forEach(this::removeChild);
        return isLeaf() && isEmpty();
    }

}
