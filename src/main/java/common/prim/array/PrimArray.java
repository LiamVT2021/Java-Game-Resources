package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface PrimArray<N extends Number> {

    int capacity();

    N get(int index);

    void set(int index, N value);

    default N swap(int index, N value) {
        N ret = get(index);
        set(index, value);
        return ret;
    }

    void forEach(Consumer<N> consumer);

    default Stream<N> stream() {
        Stream.Builder<N> builder = Stream.builder();
        forEach(builder);
        return builder.build();
    }

    default String toString(CharSequence pre, CharSequence del, CharSequence suf) {
        return stream().map(N::toString).collect(Collectors.joining(del, pre, suf));
    }

    static abstract class ADT<N extends Number, A> implements PrimArray<N> {

        public final A array;

        public ADT(A array) {
            this.array = array;
        }

        @Override
        public String toString() {
            return toString("[ ", ", ", " ]");
        }

    }

}
