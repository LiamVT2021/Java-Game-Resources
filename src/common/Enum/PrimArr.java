package common.Enum;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.Builders;

public interface PrimArr<A, N extends Number> {

    N get(int index);

    void set(int index, N value);

    int length();

    void clear();

    A array();

    Stream<N> stream();

    static abstract class ADT<A, N extends Number> implements PrimArr<A, N> {

        protected A array;

        public ADT(A array) {
            this.array = array;
        }

        @Override
        public A array() {
            return array;
        }

        @Override
        public void clear() {
            clear(length());
        }

        protected abstract void clear(int size);

    }

    static class Int extends ADT<int[], Integer> {

        public Int(int[] array) {
            super(array);
        }

        public Int(int size) {
            super(new int[size]);
        }

        @Override
        public Integer get(int index) {
            return array[index];
        }

        @Override
        public void set(int index, Integer value) {
            array[index] = value;
        }

        @Override
        public int length() {
            return array.length;
        }

        @Override
        public void clear(int size) {
            array = new int[size];
        }

        public IntStream intStream() {
            return IntStream.of(array);
        }

        @Override
        public Stream<Integer> stream() {
            return intStream().mapToObj(i -> i);
        }

    }

    static class Byte extends ADT<byte[], java.lang.Byte> {

        public Byte(byte[] array) {
            super(array);
        }

        public Byte(int size) {
            super(new byte[size]);
        }

        @Override
        public java.lang.Byte get(int index) {
            return array[index];
        }

        @Override
        public void set(int index, java.lang.Byte value) {
            array[index] = value;
        }

        @Override
        public int length() {
            return array.length;
        }

        @Override
        public void clear(int size) {
            array = new byte[size];
        }

        @Override
        public Stream<java.lang.Byte> stream() {
            return Builders.buildStream(builder -> {
                for (byte b : array)
                    builder.accept(b);
            });
        }

    }

}
