package common.tree;

import java.util.ArrayList;
import java.util.stream.Stream;

public abstract class TreeNode<E> {

    public final E item;

    public abstract Stream<? extends TreeNode<E>> next();

    TreeNode(E item) {
        this.item = item;
    }

    static class Builder<E> extends TreeNode<E> {

        final ArrayList<Builder<E>> next = new ArrayList<>(5);

        Builder(E item) {
            super(item);
        }

        @Override
        public Stream<? extends TreeNode<E>> next() {
            return next.stream();
        }

    }

    static class Final<E> extends TreeNode<E> {

        private final Final<E>[] next;

        Final(E item, Final<E>[] next) {
            super(item);
            this.next = next;
        }

        @Override
        public Stream<? extends TreeNode<E>> next() {
            return Stream.of(next);
        }
    }

}
