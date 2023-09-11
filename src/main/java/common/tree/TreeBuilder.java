package common.tree;

import common.tree.TreeNode.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class TreeBuilder<E> implements Tree<E> {

    private final Builder<E> root = new Builder<E>(null);
    private final HashMap<E, Builder<E>> map = new HashMap<>();

    @Override
    public Stream<? extends TreeNode<E>> roots() {
        return root.next();
    }

    @Override
    public Stream<? extends TreeNode<E>> getNext(E item) {
        return map.get(item).next();
    }

    @SafeVarargs
    public final void withRoots(E... roots) {
        withBranches(root, roots);
    }

    @SafeVarargs
    public final void withRoot(E root, E... next) {
        withBranches(this.root, root);
        withBranches(root, next);
    }

    @SafeVarargs
    public final void withBranches(E item, E... next) {
        withBranches(getNode(item), next);
    }

    @SafeVarargs
    private void withBranches(Builder<E> node, E... next) {
        for (E n : next)
            node.next.add(getNode(n));
    }

    private Builder<E> getNode(E item) {
        Builder<E> node = map.get(item);
        if (node == null) {
            node = new Builder<E>(item);
            map.put(item, node);
        }
        return node;
    }

    //

    public Tree<E> finalise(IntFunction<Final<E>[]> arrFunc) {
        HashMap<E, Builder<E>> builderMap = new HashMap<>(map);
        HashMap<E, Final<E>> finalMap = new HashMap<>();
        Function<ArrayList<Builder<E>>, Final<E>[]> arrayFunc = //
                next -> next.stream().map(finalMap::get).toArray(arrFunc);
        int remaining;
        while ((remaining = builderMap.size()) != 0) {
            builderMap.forEach((item, next) -> {
                if (finalMap.keySet().containsAll(List.of(next)))
                    finalMap.put(item, new Final<E>(item, arrayFunc.apply(getNode(item).next)));
            });
            if (remaining == builderMap.size())
                throw new IllegalArgumentException("Cannot finalize Cyclical Trees");
        }
        return new FinalTree<E>(arrayFunc.apply(root.next), finalMap);
    }

    private static class FinalTree<E> implements Tree<E> {

        private Final<E>[] arr;
        private HashMap<E, Final<E>> map;

        public FinalTree(Final<E>[] array, HashMap<E, Final<E>> nodeMap) {
            arr = array;
            map = nodeMap;
        }

        @Override
        public Stream<? extends TreeNode<E>> roots() {
            return Stream.of(arr);
        }

        @Override
        public Stream<? extends TreeNode<E>> getNext(E item) {
            return map.get(item).next();
        }

    }

}
