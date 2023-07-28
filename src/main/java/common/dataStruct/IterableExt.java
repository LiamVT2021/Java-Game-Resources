package common.dataStruct;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface IterableExt<E> extends Iterable<E>, Streamable<E> {

    default Stream<E> stream() {
        Stream.Builder<E> builder = Stream.builder();
        forEach(builder);
        return builder.build();
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
