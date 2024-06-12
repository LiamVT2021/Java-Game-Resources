package common.util;

import java.util.stream.Stream;

public class StreamUtils {

    @SafeVarargs
    public static <T> Stream<T> concat(Stream<? extends T>... streams) {
        int size = streams.length;
        switch (size) {
            case 0:
                return Stream.of();
            case 1:
                return streams[0].map(t -> t);
            default:
                Stream<T> ret = Stream.concat(streams[0], streams[1]);
                for (int i = 2; i < size; i++)
                    ret = Stream.concat(ret, streams[i]);
                return ret;
        }
    }

    @SafeVarargs
    public static <T> Stream<T> prepend(Stream<? extends T> stream, T... predecessors) {
        return Stream.concat(Stream.of(predecessors), stream);
    }

    @SafeVarargs
    public static <T> Stream<T> append(Stream<? extends T> stream, T... succecessors) {
        return Stream.concat(stream, Stream.of(succecessors));
    }

    public static <T> Stream<T> wrap(T predecessor, Stream<? extends T> stream, T succecessor) {
        return Stream.concat(Stream.concat(Stream.of(predecessor), stream), Stream.of(succecessor));
    }

}
