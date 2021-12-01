package heap;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface Supplied<E> extends PushPop<E> {

    public Supplier<E> supply();

    public Supplied<E> withSupply(Supplier<E> supply);

    public boolean isFull();

    public default boolean fill() {
        if (isFull())
            return true;
        Supplier<E> supply = supply();
        if (supply == null)
            return false;
        while (!isFull())
            push(supply.get());
        return true;
    }

    public static interface Prim extends PushPop.Prim {

        public IntSupplier supply();

        public Prim withSupply(IntSupplier supply);

        public boolean isFull();

        public default boolean fill() {
            if (isFull())
                return true;
            IntSupplier supply = supply();
            if (supply == null)
                return false;
            do
                push(supply.getAsInt());
            while (!isFull());
            return true;
        }
    }
}
