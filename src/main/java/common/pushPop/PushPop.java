package common.pushPop;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface PushPop<V> {

    boolean push(V value);

    V peek();

    V pop();

    default V swap(V value) {
        if (value == null || isEmpty())
            return value;
        V ret = pop();
        push(value);
        return ret;
    }

    int size();

    int capacity();

    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean isFull() {
        return size() == capacity();
    }

    default PushPop<V> fill(Supplier<V> supplier) {
        while (!isFull())
            push(supplier.get());
        return this;
    }

    default Stream<V> empty() {
        Stream.Builder<V> builder = Stream.builder();
        while (!isEmpty())
            builder.accept(pop());
        return builder.build();
    }

}
