package common.util;

import java.util.stream.Stream;

@FunctionalInterface
public interface Streamable<V> extends Iterable<V> {

    default Stream<V> stream() {
        Stream.Builder<V> builder = Stream.builder();
        forEach(builder);
        return builder.build();
    }

}
