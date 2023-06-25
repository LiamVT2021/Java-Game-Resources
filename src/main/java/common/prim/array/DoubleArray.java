package common.prim.array;

import java.util.function.Consumer;
import java.util.stream.DoubleStream;

public class DoubleArray extends PrimArray.ADT<Double, double[]> {

    public DoubleArray(int size) {
        super(new double[size]);
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
    public void set(int index, Double value) {
        array[index] = value;
    }

    @Override
    public void forEach(Consumer<Double> consumer) {
        for (double v : array)
            consumer.accept(v);
    }

    public DoubleStream longStream() {
        return DoubleStream.of(array);
    }

}
