package common.util;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MapUtils {

    public static <K, I, O, M extends Map<K, O>> M mapValues(Map<K, I> input,
            Function<I, O> mapper, Supplier<M> constructor) {
        return input.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> mapper.apply(e.getValue()),
                (a, b) -> a,
                constructor));
    }

}
