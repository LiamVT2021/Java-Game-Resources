package common.prim.array;

/**
 * A wrapper around an array of float values.
 * 
 * @version 6/30/23
 */
public class FloatArray extends PrimArray.Flt<Float, float[]> {

    public FloatArray(int size) {
        this(new float[size]);
    }

    public FloatArray(float[] array) {
        super(array);
    }

    @Override
    public long size() {
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

}
