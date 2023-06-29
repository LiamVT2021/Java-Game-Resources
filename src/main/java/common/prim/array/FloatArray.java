package common.prim.array;

import java.util.function.Consumer;

/**
 * A wrapper around an array of float values.
 * 
 * @version 6/29/23
 */
public class FloatArray extends PrimArray.Flt<Float, float[]> {

    public FloatArray(int size) {
        this(new float[size]);
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
    public void set(int index, Number value) {
        array[index] = value.floatValue();
    }

    @Override
    public Float cast(Number value) {
        return value.floatValue();
    }

    @Override
    public void forEach(Consumer<? super Float> consumer) {
        for (float v : array)
            consumer.accept(v);
    }

}
