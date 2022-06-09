package grid;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class Grid extends JPanel {

    protected GridCell[][] cells;
    protected Tile[][] tiles;
    protected int numColumns, numRows, cellWidth, cellHeight, scale;
    protected int[] polyX, polyY, highX, highY;

    public Grid(int width, int height, int scale) {
        cells = new GridCell[width][height];
        tiles = new Tile[width][height];
        highX = new int[sides()];
        highY = new int[sides()];
        this.numColumns = width;
        this.numRows = height;
        setScale(scale);
        allCells((x, y) -> {
            cells[x][y] = new GridCell();
            tiles[x][y] = makeTile(x, y);
        });
    }

    public void setScale(int scale) {
        setBackground(Color.BLUE);
        this.scale = scale;
        makePoly();
        for (int i = 0; i < sides(); i++) {
            highX[i] = polyX[i] * 5 / 6;
            highY[i] = polyY[i] * 5 / 6;
        }
        Dimension d = dimensions();
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
    }

    public Dimension dimensions() {
        return new Dimension((numColumns + 1) * cellWidth, (numRows + 1) * cellHeight);
    }

    public abstract void makePoly();

    public abstract int sides();

    public Tile makeTile(int x, int y) {
        int centerX = centerX(x, y);
        int centerY = (y + 1) * cellHeight;
        Polygon outer = new Polygon();
        Polygon inner = new Polygon();
        for (int i = 0; i < sides(); i++) {
            outer.addPoint(polyX[i] + centerX, polyY[i] + centerY);
            inner.addPoint(highX[i] + centerX, highY[i] + centerY);
        }
        return new Tile(centerX, centerY, outer, inner);
    }

    protected abstract int centerX(int x, int y);

    private void draw(Graphics map, int x, int y) {
        cells[x][y].draw(map, tiles[x][y], scale);
    }

    @Override
    protected void paintComponent(Graphics map) {
        super.paintComponent(map);
        allCells((x, y) -> draw(map, x, y));
    }

    @FunctionalInterface
    public interface CoordFunc {
        public void apply(int x, int y);
    }

    protected void allCells(CoordFunc func) {
        for (int y = 0; y < numRows; y++)
            for (int x = 0; x < numColumns; x++)
                func.apply(x, y);
    }

    protected void allCells(Consumer<GridCell> func) {
        Stream.of(cells).parallel().forEach(col -> Stream.of(col).parallel().forEach(func));
    }

    public String coords(int x, int y) {
        return vector(x - numColumns / 2, y - numRows / 2);
    }

    public abstract String vector(int x, int y);

    public abstract int distance(int x, int y);

}
