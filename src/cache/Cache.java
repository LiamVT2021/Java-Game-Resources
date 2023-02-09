package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Cache<V> {

    private final Map<String, Key> keys = new HashMap<>();

    public abstract class Key {
        public final String str;

        public Key(String str) {
            if (keys.containsKey(str))
                throw new RuntimeException("key named " + str + " already present in cache.");
            this.str = str;
            keys.put(str, this);
        }

        public abstract V get(Instance cache);
    }

    public final class Func extends Key {
        private final Function<Instance, V> func;

        public Func(String str, Function<Instance, V> func) {
            super(str);
            this.func = func;
        }

        @Override
        public V get(Cache<V>.Instance cache) {
            return func.apply(cache);
        }
    }

    public final class Base extends Key {
        private final V base;

        public Base(String str, V base) {
            super(str);
            this.base = base;
        }

        @Override
        public V get(Cache<V>.Instance cache) {
            return base;
        }
    }

    public final class Instance {
        private final Map<Key, V> set = new HashMap<>();
        private final Map<Key, V> cache = new HashMap<>();

        public V calc(Key key) {
            return key.get(this);
        }

        public V get(Key key) {
            return Utils.firstNotNull(key, set::get, cache::get, this::calc);
        }

        public void set(Key key, V value) {
            set.put(key, value);
        }
    }

}
