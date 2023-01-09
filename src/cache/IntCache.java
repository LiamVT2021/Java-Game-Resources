package cache;

import java.util.stream.Stream;

public class IntCache extends Cache<Integer> {

    public Key base(String str) {
        return base(str, 0);
    }

    public Key sum(String str, Key... keys) {
        return new Key(str, i -> Stream.of(keys).mapToInt(i::calc).sum());
    }

}
