package common.dataStruct;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface IterableExt<E> extends Iterable<E>, Streamable<E> {

    default Stream<E> stream() {
        Stream.Builder<E> builder = Stream.builder();
        forEach(builder);
        return builder.build();
    }

    @Override
    default long count() {
        int count = 0;
        for (@SuppressWarnings("unused")
        E e : this)
            count++;
        return count;
    }

    @Override
    default E getFirst(Predicate<E> pred) {
        if (pred == null)
            return null;
        for (E e : this)
            if (pred.test(e))
                return e;
        return null;
    }

    @Override
    default boolean has(Predicate<E> pred) {
        return getFirst(pred) != null;
    }

    default Supplier<E> loop() {
        return new Supplier<>() {
            private Iterator<E> it;

            @Override
            public E get() {
                if (it.hasNext())
                    return it.next();
                it = iterator();
                return it.hasNext() ? it.next() : null;
            }
        };
    }

}
