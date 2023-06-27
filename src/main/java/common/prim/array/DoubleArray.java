package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.DoubleStream;

/**
 * A wrapper around an array of double values.
 * 
 * @version 6/27/23
 */
public class DoubleArray extends PrimArray.ADT<Double, double[]> {

    public DoubleArray(int size) {
        this(new double[size]);
    }

    public DoubleArray(double[] array) {
        super(array);
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public Double get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, Number value) {
        array[index] = value.doubleValue();
    }

    @Override
    public void forEach(Consumer<Double> consumer) {
        for (double v : array)
            consumer.accept(v);
    }

    /**
     * @return a DoubleStream of the values in the array
     */
    public DoubleStream doubleStream() {
        return DoubleStream.of(array);
    }

}
