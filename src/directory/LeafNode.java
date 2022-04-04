package directory;

import java.util.Map;

public interface LeafNode<E extends DirNode<E>> extends DirNode<E> {

    @Override
    default Map<String, E> children() {
        return null;
    }

    @Override
    default boolean isLeaf() {
        return true;
    }

    @Override
    default boolean put(E child) {
        return false;
    }

}
