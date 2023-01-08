package grid;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.swing.JFrame;

/**
 * Square Grid
 * 
 * @author Liam Snyder
 * @version 9/4/22
 */
public class SquareGrid extends Grid {

    public SquareGrid(int width, int height, int scale) {
        super(width, height, scale);
    }

    @Override
    public int sides() {
        return 4;
    }

    @Override
    public void makePoly() {
        polyX = new int[] { -scale, scale, scale, -scale };
        polyY = new int[] { -scale, -scale, scale, scale };
        cellWidth = 2 * scale;
        cellHeight = 2 * scale;
    }

    @Override
    public int centerX(int x, int y) {
        return (x + 1) * cellWidth;
    }

    @Override
    public String vector(int x, int y) {
        return "(" + x + ", " + -y + ")";
    }

    @Override
    public int distance(int x, int y) {
        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public Point clickLoc(MouseEvent e) {
        return new Point(fromClick(e.getX()), fromClick(e.getY()));
    }

    private int fromClick(int v) {
        int n = v / scale;
        return n <= 0 ? -1 : (n - 1) / 2;
    }

    // Shapes

    @Override
    public Map<String, Shape.Builder> shapes() {
        return Map.of("Rectangle", Rectangle::new, "Box", Box::new);
    }

    private abstract class Rect implements Shape {

        protected final int xMin, yMin, xMax, yMax;

        public Rect(int x1, int y1, int x2, int y2) {
            xMin = Math.min(x1, x2);
            yMin = Math.min(y1, y2);
            xMax = Math.max(x1, x2);
            yMax = Math.max(y1, y2);
        }

        protected boolean isIn(int min, int value, int max) {
            return min <= value && value <= max;
        }

    }

    private class Rectangle extends Rect {

        public Rectangle(int x1, int y1, int x2, int y2) {
            super(x1, y1, x2, y2);
        }

        @Override
        public void allCoords(BiConsumer<Integer, Integer> func) {
            for (int x = xMin; x <= xMax; x++)
                for (int y = yMin; y <= yMax; y++)
                    func.accept(x, y);
        }

        @Override
        public boolean contains(int x, int y) {
            return isIn(xMin, x, xMax) && isIn(yMin, y, yMax);
        }

    }

    private class Box extends Rect {

        public Box(int x1, int y1, int x2, int y2) {
            super(x1, y1, x2, y2);
        }

        @Override
        public void allCoords(BiConsumer<Integer, Integer> func) {
            int x = xMin, y = yMin;
            for (; x < xMax; x++)
                func.accept(x, y);
            for (; y < yMax; y++)
                func.accept(x, y);
            for (; x > xMin; x--)
                func.accept(x, y);
            for (; y > yMin; y--)
                func.accept(x, y);
        }

        @Override
        public boolean contains(int x, int y) {
            return ((xMin == x || x == xMax) && isIn(yMin, y, yMax))
                    || ((yMin == y || y == yMax) && isIn(xMin, x, xMax));
        }

    }

}
