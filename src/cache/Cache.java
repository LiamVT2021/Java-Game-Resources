package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Cache<V> {

    private final Map<String, Key> keys = new HashMap<>();

    public final class Key {
        public final String str;
        private final Function<Instance, V> func;

        public Key(String str, Function<Instance, V> func) {
            if (keys.containsKey(str))
                throw new RuntimeException("key named " + str + " already present in cache.");
            this.str = str;
            this.func = func;
            keys.put(str, this);
        }

        public V get(Instance cache) {
            return func.apply(cache);
        }
    }

    public Key base(String str, V base) {
        return new Key(str, i -> base);
    }

    public final class Instance {
        private final Map<Key, V> set = new HashMap<>();
        private final Map<Key, V> cache = new HashMap<>();

        public V calc(Key key) {
            return key.get(this);
        }

        public V get(Key key) {
            return Utils.firstNotNull(key,  set::get, cache::get, this::calc);
        }

        public void set(Key key, V value) {
            set.put(key, value);
        }
    }

}
