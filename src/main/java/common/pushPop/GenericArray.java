package common.pushPop;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Class for wrapping a generic array
 * 
 * @param V the generic type stored in this array
 * @version 6/29/23
 */
public class GenericArray<V> extends ArrayWrapper.ADT<V, V, V[]> {

    public GenericArray(V[] array) {
        super(array);
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
    public V remove(int index) {
        V ret = get(index);
        set(index, null);
        return ret;
    }

    @Override
    public void set(int index, V value) {
        array[index] = value;
    }

    @Override
    public V cast(V value) {
        return value;
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
