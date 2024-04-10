package common.prim.array;

import java.util.stream.DoubleStream;

/**
 * A wrapper around an array of double values.
 * 
 * @version 4/10/24
 */
public class DoubleArray extends PrimArray.Flt<Double, double[]> {

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
    public Double cast(Number value) {
        return value.doubleValue();
    }

    /**
     * @return a DoubleStream of the values in the array
     */
    public DoubleStream doubleStream() {
        return DoubleStream.of(array);
    }

    @Override
    public Double sum(Number a, Number b) {
        return a.doubleValue() + b.doubleValue();
    }

    @Override
    public Double product(Number a, Number b) {
        return a.doubleValue() * b.doubleValue();
    }

}
