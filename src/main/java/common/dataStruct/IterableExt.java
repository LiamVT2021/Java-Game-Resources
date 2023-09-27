package common.dataStruct;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * default methods for Iterable
 * 
 * @param <E> the type returned by the Iterator
 * @version 9/22/23
 */
@FunctionalInterface
public interface IterableExt<E> extends Iterable<E>, Searchable<E> {

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
