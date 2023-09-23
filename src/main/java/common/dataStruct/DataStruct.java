package common.dataStruct;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface DataStruct<E> extends Iterable<E>, Streamable<E> {

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
        return useStream() ? Streamable.super.getFirst(pred) : getFirstIterator(pred);
    }

    default E getFirstIterator(Predicate<E> pred) {
        if (pred == null)
            return null;
        for (E e : this)
            if (pred.test(e))
                return e;
        return null;
    }

    @Override
    default boolean has(Predicate<E> pred) {
        return useStream() ? Streamable.super.has(pred) : getFirstIterator(pred) != null;
    }

    default Supplier<E> loop() {
        return new Supplier<>() {
            private Iterator<E> it = iterator();

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
