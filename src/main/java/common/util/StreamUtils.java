package common.util;

import java.util.stream.Stream;

public class StreamUtils {

    public static <T> Stream<T> wrap(T predecessor, Stream<? extends T> stream, T succecessor) {
        return Stream.concat(Stream.concat(Stream.of(predecessor), stream), Stream.of(succecessor));
    }

}
