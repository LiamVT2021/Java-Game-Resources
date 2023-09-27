package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.DoubleStream;

/**
 * A wrapper around an array of double values.
 * 
 * @version 6/30/23
 */
public class DoubleArray extends PrimArray.Flt<Double, double[]> {

    public DoubleArray(int size) {
        this(new double[size]);
    }

    public DoubleArray(double... array) {
        super(array);
    }

    @Override
    public long size() {
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

    @Override
    public void forEach(Consumer<? super Double> func) {
        for (double d : array)
            func.accept(d);
    }

    @Override
    public DoubleStream doubleStream() {
        return DoubleStream.of(array);
    }

}
