package directory;

public interface LeafNode extends DirNodeADT<DirNodeADT<?>> {

    @Override
    default boolean isLeaf() {
        return true;
    }

    @Override
    default boolean addChild(DirNodeADT<?> child) {
        return false;
    }

    @Override
    default boolean prune() {
        return isEmpty();
    }

}
