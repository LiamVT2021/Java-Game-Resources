package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Builders {

    private static <B, R> R build(Supplier<B> fresh, Consumer<B> func, Function<B, R> build) {
        B builder = fresh.get();
        func.accept(builder);
        return build.apply(builder);
    }

    public static <R> Stream<R> buildStream(Consumer<Stream.Builder<R>> func) {
        return build(Stream::builder, func, Stream.Builder::build);
    }

    public static String buildString(Consumer<StringBuilder> func) {
        return build(StringBuilder::new, func, StringBuilder::toString);
    }

    public static <E> List<E> buildList(int size, Consumer<List<E>> func) {
        return build(() -> new ArrayList<>(size), func, l -> l);
    }

    public static <K, V> Map<K, V> buildMap(Consumer<Map<K, V>> func) {
        return build(HashMap::new, func, m -> m);
    }

}
