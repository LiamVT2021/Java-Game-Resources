package common.pushPop;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

import common.dataStruct.IterableExt;
import common.util.StringUtils;

/**
 * A data structure with push, pop, peek, and swap methods.
 * 
 * @param G the type returned by get methods
 * @param S the type consumed by set methods
 * @version 7/15/23
 */
public interface PushPop<G, S> extends IterableExt<G> {

    boolean isEmpty();

    boolean isFull();

    /**
     * Attempts to insert the value into the structure.
     * 
     * @return False if value is null or the structure is full
     */
    boolean push(S value);

    /**
     * @return the next element in the data structure, null if empty.
     */
    G peek();

    /**
     * Removes an element from the data structure.
     * 
     * @return the removed element, null if empty.
     */
    G pop();

    /**
     * Inserts and removes elements at the same time.
     * 
     * @param value the new element, will perform pop if the value is null.
     * @return the removed element, value if empty.
     */
    G swap(S value);

    /**
     * Pushes elements until the data structure is full.
     * 
     * @param supplier a method suppling new elements
     * @return this data structure, for the purpose of chaining.
     */
    default PushPop<G, S> fill(Supplier<S> supplier) {
        while (!isFull())
            push(supplier.get());
        return this;
    }

    /**
     * @return an Iterable object which will pop elements until empty
     */
    default IterableExt<G> popIt() {
        return () -> new Iterator<G>() {

            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public G next() {
                return pop();
            }

        };
    }

    /**
     * pops all the elements in the data structure.
     * 
     * @return a Stream of the removed elements
     */
    default Stream<G> empty() {
        return popIt().stream();
    }

    /**
     * @return a String representation of values stored in the datastruture
     */
    default String values() {
        return StringUtils.join("< ", ", ", " >", stream().map(G::toString));
    }

}
