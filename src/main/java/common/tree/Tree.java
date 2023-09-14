package common.tree;

import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Tree<E> {

    Stream<? extends TreeNode<E>> roots();

    Stream<? extends TreeNode<E>> getNext(E item);

    default Stream<E> traverse(Predicate<E> include, Predicate<E> next) {
        return roots().flatMap(node -> node.traverse(include, next));
    }

}
