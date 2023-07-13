package common.pushPop;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

import common.util.Streamable;
import common.util.StringUtils;

/**
 * A data structure with push, pop, peek, and swap methods.
 * 
 * @param G the type returned by get methods
 * @param S the type consumed by set methods
 * @version 7/2/23
 */
public interface PushPop<G, S> extends Streamable<G> {

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
     * @param value the new element
     * @return the removed element, value if empty, null if value is null.
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
    default Streamable<G> popIt() {
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
     * @return a brief description of the data stucture
     */
    default String preview() {
        return "Peek: " + peek();
    }

    /**
     * @return a visual representation of the datastruture
     */
    default String display() {
        return StringUtils.join("< ", ", ", " >", stream().map(G::toString));
    }

    /**
     * A PushPop backed by an ArrayWrapper.
     * 
     * @param G the type returned by get methods
     * @param S the type consumed by set methods
     * @param A the type of the wrapped array
     * @version 7/2/23
     */
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

        /**
         * Attempts to push a Collection of values.
         * 
         * @return False if there is not enough room, or any of the values are null.
         */
        public boolean pushAll(Collection<S> values) {
            if (values.size() > capacity() - size() || values.stream().anyMatch(v -> v == null))
                return false;
            values.forEach(this::push);
            return true;
        }

        @Override
        public String preview() {
            return PushPop.super.preview() + "\n " + size() + " / " + capacity();
        }

        /**
         * @return a String of raw values stored in the array
         */
        public String arrayString() {
            return array.arrayString();
        }

        @Override
        public String toString() {
            return preview() + '\n' + arrayString() + '\n' + display();
        }

    }

}
