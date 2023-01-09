package cache;

import java.util.function.Function;

public class Utils {

    public static <K, V> V firstNotNull(K key, Function<K, V>... funcs) {
        for (Function<K, V> func : funcs) {
            V value = func.apply(key);
            if (value != null)
                return value;
        }
        return null;
    }

}
