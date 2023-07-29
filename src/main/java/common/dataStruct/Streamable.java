package common.dataStruct;

import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface Streamable<E> {

    Stream<E> stream();

    default long size() {
        return stream().count();
    }

    /**
     * @return The first element that satisfies the Predicate,
     *         null if none found or pred is null
     */
    default E getFirst(Predicate<E> pred) {
        return pred == null ? null : stream().filter(pred).findFirst().orElse(null);
    }

    /**
     * @return The first element that satisfies the Object's equals method,
     *         null if none found or obj is null
     */
    default E getFirst(Object obj) {
        return obj == null ? null : getFirst(obj::equals);
    }

    /**
     * @return True if any element satisfies the Predicate,
     *         False if none found or pred is null
     */
    default boolean has(Predicate<E> pred) {
        return pred == null ? false : stream().anyMatch(pred);
    }

    /**
     * @return True if any element satisfies the Object's equals method,
     *         False if none found or obj is null
     */
    default boolean has(Object obj) {
        return obj == null ? false : has(obj::equals);
    }

}
