package common.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface StringUtils {

    static String NULL_STRING = "null";

    static String handleNull(Object obj) {
        return obj == null ? NULL_STRING : obj.toString();
    }

    static String join(CharSequence delim, Stream<CharSequence> stream) {
        return stream.collect(Collectors.joining(delim));
    }
    static String join(CharSequence prefix, CharSequence delim, CharSequence suffix, Stream<CharSequence> stream) {
        return stream.collect(Collectors.joining(delim, prefix, suffix));
    }

}
