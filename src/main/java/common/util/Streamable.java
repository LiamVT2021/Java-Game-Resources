package common.util;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface Streamable<V> extends Iterable<V> {

    default Stream<V> stream() {
        Stream.Builder<V> builder = Stream.builder();
        forEach(builder);
        return builder.build();
    }

    default Supplier<V> loop() {
        return new Supplier<>() {
            private boolean isEmpty;
            private Iterator<V> it;

            @Override
            public V get() {
                if (isEmpty)
                    return null;
                if (it.hasNext())
                    return it.next();
                it = iterator();
                if (it.hasNext())
                    return it.next();
                isEmpty = true;
                return null;
            }
        };
    }

}
