package common;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamUtils {

    public static <R> Stream<R> buildStream(Consumer<Stream.Builder<R>> func) {
        Stream.Builder<R> builder = Stream.builder();
        func.accept(builder);
        return builder.build();
    }

}
