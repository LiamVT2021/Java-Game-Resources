package common.tree;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class TreeNode<E> {

    public final E item;

    public abstract Stream<? extends TreeNode<E>> next();

    public Stream<E> traverse(Predicate<E> include, Predicate<E> next) {
        if (!next.test(item))
            return include.test(item) ? Stream.of(item) : Stream.of();
        Stream<E> ret = next().flatMap(node -> node.traverse(include, next));
        return include.test(item) ? Stream.concat(Stream.of(item), ret) : ret;
    }

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
