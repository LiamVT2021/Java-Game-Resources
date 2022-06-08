package grid;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

public abstract class Grid extends JPanel {

    protected GridCell[][] cells;
    protected Tile[][] tiles;
    protected int X, Y, scale, rotation;
    protected int[] polyX, polyY, highX, highY;

    public Grid(int X, int Y, int scale, int rotation) {
        cells = new GridCell[X][Y];
        tiles = new Tile[X][Y];
        highX = new int[sides()];
        highY = new int[sides()];
        this.X = X;
        this.Y = Y;
        setScale(scale, rotation);
        allCells((x, y) -> {
            cells[x][y] = new GridCell();
            tiles[x][y] = makeTile(x, y);
        });
        cells[4][4].borderColor = Color.RED;
    }

    protected void setScale(int scale, int rotation) {
        setBackground(Color.BLUE);
        this.scale = scale;
        this.rotation = rotation;
        makePoly();
        setSize();
    }

    private void setSize() {
        Dimension d = dimensions();
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
    }

    public abstract Dimension dimensions();

    public void makePoly() {
        for (int i = 0; i < sides(); i++) {
            highX[i] = polyX[i] * 5 / 6;
            highY[i] = polyY[i] * 5 / 6;
        }
    }

    public abstract int sides();

    public Tile makeTile(int x, int y) {
        Polygon outer = new Polygon();
        Polygon inner = new Polygon();
        for (int i = 0; i < sides(); i++) {
            outer.addPoint(polyX[i] + x, polyY[i] + y);
            inner.addPoint(highX[i] + x, highY[i] + y);
        }
        return new Tile(x, y, outer, inner);
    }

    private void draw(Graphics map, int x, int y) {
        cells[x][y].draw(map, tiles[x][y], scale, rotation);
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
        for (int y = 0; y < Y; y++)
            for (int x = 0; x < X; x++)
                func.apply(x, y);
    }

}
