package common.dataStruct;

import java.util.stream.Stream;

import common.util.StringUtils;

/**
 * Class for wrapping a generic array
 * 
 * @param <V> the generic type stored in this array
 * @version 6/30/23
 */
public class GenericArray<V> extends ArrayWrapper.ADT<V, V, V[]> {

    public GenericArray(V[] array) {
        super(array);
    }

    @Override
    public long size() {
        return array.length;
    }

    @Override
    public V get(int index) {
        return array[index];
    }

    @Override
    public V remove(int index) {
        return swap(index, null);
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
    public Stream<V> stream() {
        return Stream.of(array);
    }

    @Override
    public String toString(CharSequence prefix, CharSequence delim, CharSequence suffix) {
        return StringUtils.join(prefix, delim, suffix, stream().map(StringUtils::handleNull));
    }

}
