package common;

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

    public static String buildString(Consumer<StringBuilder> func){
        return build(StringBuilder::new, func, StringBuilder::toString);
    }

}
