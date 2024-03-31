package common.util;

import java.util.stream.Stream;

public class StreamUtils {

    public <T> Stream<T> concat(@SuppressWarnings("unchecked") Stream<? extends T>... streams) {
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

}
