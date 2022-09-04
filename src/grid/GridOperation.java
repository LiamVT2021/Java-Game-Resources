package grid;

import java.awt.Color;
import java.awt.Point;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JLabel;

public abstract class GridOperation {

    protected Grid grid;

    public GridOperation(Grid grid) {
        this.grid = grid;
    }

    public boolean onClick(Point p) {
        return grid.inbounds(p.x, p.y) ? onClick(p.x, p.y) : false;
    }

    public abstract boolean onClick(int x, int y);

    public boolean onHover(Point p) {
        return onHover(p.x, p.y);
    }

    public abstract boolean onHover(int x, int y);

    public abstract Color highlight(int x, int y);

    public abstract void reset();

    public static class Simple extends GridOperation {

        private Consumer<GridCell> func;

        public Simple(Grid grid, Consumer<GridCell> function) {
            super(grid);
            func = function;
        }

        @Override
        public boolean onClick(int x, int y) {
            func.accept(grid.cells[x][y]);
            return true;
        }

        @Override
        public boolean onHover(int x, int y) {
            return false;
        }

        @Override
        public Color highlight(int x, int y) {
            return null;
        }

        @Override
        public void reset() {
        }

    }

    public static class DrawShape extends GridOperation {

        public DrawShape(Grid grid, Supplier<Shape.Builder> builder,
                Supplier<Color> highlight, Supplier<Consumer<GridCell>> func) {
            super(grid);
            this.builder = builder;
            this.highlight = highlight;
            this.func = func;
        }

        private int x1, y1, x2, y2;
        private Shape shape;
        private boolean started;
        private boolean active;
        private final Supplier<Shape.Builder> builder;
        private final Supplier<Color> highlight;
        private final Supplier<Consumer<GridCell>> func;

        @Override
        public boolean onClick(int x, int y) {
            Shape.Builder build = builder.get();
            if (build == null) {
                func.get().accept(this.grid.cells[x][y]);
            } else if (started) {
                build.make(x1, y1, x, y).allCoords((X, Y) -> func.get().accept(this.grid.cells[X][Y]));
                started = false;
            } else {
                x1 = x;
                y1 = y;
                started = true;
                return false;
            }
            return true;
        }

        @Override
        public boolean onHover(int x, int y) {
            boolean old = active;
            active = (grid.inbounds(x, y));
            if (!active)
                return old;
            Shape.Builder build = builder.get();
            if (build == null || started) {
                if (x2 != x || y2 != y) {
                    x2 = x;
                    y2 = y;
                    if (build != null)
                        shape = build.make(x1, y1, x2, y2);
                    return true;
                }
                return !old;
            }
            return false;
        }

        @Override
        public Color highlight(int x, int y) {
            return active && ((shape == null) ? (x == x2 && y == y2) : shape.contains(x, y)) ? highlight.get() : null;
        }

        @Override
        public void reset() {
            started = false;
        }

    }

    public static abstract class OneTile extends GridOperation {

        protected int x, y;

        public OneTile(Grid grid) {
            super(grid);
        }

        @Override
        public boolean onHover(int x, int y) {
            boolean ret = this.x != x || this.y != y;
            this.x = x;
            this.y = y;
            return ret;
        }

        @Override
        public Color highlight(int x, int y) {
            return (this.x == x && this.y == y) ? color() : null;
        }

        abstract Color color();

    }

    public static class Add extends OneTile {

        private final Supplier<Actor> actor;
        private final Supplier<Color> color;

        public Add(Grid grid, Supplier<Actor> actor, Supplier<Color> color) {
            super(grid);
            this.actor = actor;
            this.color = color;
        }

        @Override
        public boolean onClick(int x, int y) {
            GridCell cell = grid.cells[x][y];
            if (cell.getActor() == null) {
                cell.setActor(actor.get());
                return true;
            }
            return false;
        }

        @Override
        public Color highlight(int x, int y) {
            return grid.cells[x][y].getActor() == null ? super.highlight(x, y) : null;
        }

        @Override
        public void reset() {
        }

        @Override
        Color color() {
            return color.get();
        }

    }

    public static class Move extends OneTile {

        public Move(Grid grid, JLabel label) {
            super(grid);
            this.label = label;
            label.setOpaque(true);
        }

        private final JLabel label;
        private GridCell prev;

        @Override
        public boolean onClick(int x, int y) {
            if (prev == null) {
                prev = grid.cells[x][y];
                this.x = x;
                this.y = y;
                Actor actor = prev.getActor();
                if (actor != null) {
                    label.setText(actor.logo());
                    label.setBackground(actor.color());
                } else
                    reset();
            } else {
                GridCell next = grid.cells[x][y];
                if (next.getActor() == null) {
                    next.setActor(prev.getActor());
                    prev.setActor(null);
                    reset();
                    return true;
                }
            }
            return false;
        }

        @Override
        public Color highlight(int x, int y) {
            return prev != null && grid.cells[x][y].getActor() == null ? super.highlight(x, y) : null;
        }

        @Override
        public void reset() {
            prev = null;
            label.setText("  ");
            label.setBackground(null);
        }

        @Override
        Color color() {
            return prev.getActor().color();
        }

    }
}
