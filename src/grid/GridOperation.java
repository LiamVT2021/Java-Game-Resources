package grid;

import java.awt.Color;
import java.awt.Point;
import java.util.function.Consumer;

public abstract class GridOperation {

    private Grid grid;

    public GridOperation(Grid grid) {
        this.grid = grid;
    }

    public void onClick(Point p) {
        if (grid.inbounds(p.x, p.y))
            onClick(p.x, p.y);
    }

    public abstract void onClick(int x, int y);

    public void onHover(Point p) {
        if (grid.inbounds(p.x, p.y))
            onHover(p.x, p.y);
    }

    public abstract void onHover(int x, int y);

    public abstract Color highlight(int x, int y);

    public class DrawShape extends GridOperation {
        public DrawShape(Grid grid, Shape.Builder builder) {
            super(grid);
            this.builder = builder;
        }

        private int x1, y1, x2, y2;
        private Shape shape;
        private boolean started;
        private Shape.Builder builder;
        private Color highlight;
        private Consumer<GridCell> func;

        @Override
        public void onClick(int x, int y) {
            if (builder == null) {
                func.accept(grid.cells[x][y]);
            } else if (started) {
                builder.make(x1, y1, x, y).allCoords((X, Y) -> func.accept(grid.cells[X][Y]));
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
