package common.dataStruct;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * default methods for Stream's
 * 
 * @param <E> the type returned by this stream
 * @version 9/22/23
 */
@FunctionalInterface
public interface Streamable<E> extends Searchable<E> {

    Stream<E> stream();

    @Override
    default E getFirst(Predicate<E> pred) {
        return pred == null ? null : stream().filter(pred).findFirst().orElse(null);
    }

    @Override
    default boolean has(Predicate<E> pred) {
        return pred == null ? false : stream().anyMatch(pred);
    }

}
