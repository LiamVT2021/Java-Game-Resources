package common.tree;

import java.util.stream.Stream;

public interface Tree<E> {

    Stream<? extends TreeNode<E>> roots();

    Stream<? extends TreeNode<E>> getNext(E item);

}
