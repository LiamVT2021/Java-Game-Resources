package grid;

import java.util.function.BiConsumer;

public interface Shape {

    @FunctionalInterface
    public interface Builder {
        public Shape make(int x1, int y1, int x2, int y2);
    }

    public abstract boolean contains(int x, int y);

    public abstract void allCoords(BiConsumer<Number, Number> func);

    public static abstract class Circle implements Shape {

        private int cx, cy, r;

        public Circle(int centerX, int centerY, int radius) {
            cx = centerX;
            cy = centerY;
            r = radius;
        }

        public abstract int distance(int dx, int dy);

        @Override
        public boolean contains(int x, int y) {
            return distance(x - cx, y - cy) <= r;
        }

    }

}
