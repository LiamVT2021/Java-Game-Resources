package common.pushPop;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface PushPop<G, S> {

    boolean isEmpty();

    boolean isFull();

    boolean push(S value);

    G peek();

    G pop();

    G swap(S value);

    default PushPop<G, S> fill(Supplier<S> supplier) {
        while (!isFull())
            push(supplier.get());
        return this;
    }

    default Stream<G> empty() {
        Stream.Builder<G> builder = Stream.builder();
        while (!isEmpty())
            builder.accept(pop());
        return builder.build();
    }

    static abstract class Array<G extends S, S, A> implements PushPop<G, S> {

        protected final ArrayWrapper<G, S, A> array;
        protected int size;

        protected Array(ArrayWrapper<G, S, A> array) {
            this.array = array;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return array.capacity();
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public boolean isFull() {
            return size() == capacity();
        }

        public boolean pushAll(Collection<S> values) {
            if (values.size() > capacity() - size() || values.stream().anyMatch(v -> v == null))
                return false;
            values.forEach(this::push);
            return true;
        }

        public String arrayString() {
            return array.toString();
        }

    }

}
