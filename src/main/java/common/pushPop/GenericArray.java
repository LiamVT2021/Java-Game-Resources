package common.pushPop;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class GenericArray<V> implements ArrayWrapper<V, V, V[]> {

    private final V[] array;

    public GenericArray(V[] array) {
        this.array = array;
    }

    @Override
    public V[] array() {
        return array;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public V get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, V value) {
        array[index] = value;
    }

    @Override
    public void forEach(Consumer<? super V> consumer) {
        for (V value : array)
            consumer.accept(value);
    }

    @Override
    public Stream<V> stream() {
        return Stream.of(array);
    }

}
