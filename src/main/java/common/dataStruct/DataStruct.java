package common.dataStruct;

import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @param <E> the type of element stored in the DataStruct
 * @version 9/22/23
 */
public interface DataStruct<E> extends IterableExt<E>, Streamable<E> {

    long size();

    default boolean useParrallel() {
        return size() >= 128;
    }

    default boolean useStream() {
        return useParrallel();
    }

    @Override
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), useParrallel());
    }

    @Override
    default E getFirst(Predicate<E> pred) {
        return useStream() ? Streamable.super.getFirst(pred) : IterableExt.super.getFirst(pred);
    }

    @Override
    default boolean has(Predicate<E> pred) {
        return useStream() ? Streamable.super.has(pred) : IterableExt.super.has(pred);
    }

}
