package grid;

import java.awt.Color;
import java.util.function.Consumer;

public interface GridOperation {

    public void onClick(int x, int y);

    public void onHover(int x, int y);

    public Color highlight(int x, int y);

    public class DrawShape implements GridOperation {

        private int x1, y1, x2, y2;
        private Shape shape;
        private boolean started;
        private Shape.Builder builder;
        private Color highlight;
        private Consumer<Shape> func;

        @Override
        public void onClick(int x, int y) {
            if (started) {
                func.accept(builder.make(x1, y1, x, y));
                started = false;
            } else {
                x1 = x;
                x2 = y;
                started = true;
            }
        }

        @Override
        public void onHover(int x, int y) {
            if (started) {
                if (x2 != x || y2 != y) {
                    x2 = x;
                    y2 = y;
                    shape = builder.make(x1, y1, x2, y2);
                }
            }
        }

        @Override
        public Color highlight(int x, int y) {
            return shape.contains(x, y) ? highlight : null;
        }

    }

}
