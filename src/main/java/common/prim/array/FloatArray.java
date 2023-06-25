package common.prim.array;

import java.util.function.Consumer;

public class FloatArray extends PrimArray.ADT<Float, float[]> {

    public FloatArray(int size) {
        super(new float[size]);
    }

    public FloatArray(float[] array) {
        super(array);
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public Float get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, Float value) {
        array[index] = value;
    }

    @Override
    public void forEach(Consumer<Float> consumer) {
        for (float v : array)
            consumer.accept(v);
    }

}
