package common.dataStruct;

import java.util.function.Predicate;

public interface Searchable<E> {

    /**
     * @return The first element that satisfies the Predicate,
     *         null if none found or pred is null
     */
    E getFirst(Predicate<E> pred);

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
    boolean has(Predicate<E> pred);

    /**
     * @return True if any element satisfies the Object's equals method,
     *         False if none found or obj is null
     */
    default boolean has(Object obj) {
        return obj == null ? false : has(obj::equals);
    }

}
