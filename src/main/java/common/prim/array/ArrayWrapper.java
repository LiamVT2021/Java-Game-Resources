package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wrapper interface around an array.
 * 
 * @version 6/27/23
 */
public interface ArrayWrapper<G, S> {

    /**
     * @return the capacity of this array
     */
    int capacity();

    /**
     * @return the value stored at this index in the array
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    G get(int index);

    /**
     * stores value at index in the array
     * 
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    void set(int index, S value);

    /**
     * performs a set and get at the same time
     * stores value at index in the array
     * 
     * @return the value that was at index in the array
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    default G swap(int index, S value) {
        G ret = get(index);
        set(index, value);
        return ret;
    }

    /**
     * Iterates over each value in the array
     * 
     * @param consumer method inside for loop
     */
    void forEach(Consumer<? super G> consumer);

    /**
     * @return a Stream of the values in the array
     */
    Stream<G> stream();

    /**
     * @param prefix characters at the begining of merged string
     * @param delim  characters betewen elements the array
     * @param suffix characters at the end of merged string
     * @return a merged string of the elements of this array
     */
    default String toString(CharSequence prefix, CharSequence delim, CharSequence suffix) {
        return stream().map(G::toString).collect(Collectors.joining(delim, prefix, suffix));
    }

}
