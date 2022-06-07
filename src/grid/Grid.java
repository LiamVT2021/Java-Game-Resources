package grid;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Grid extends JPanel {

    protected GridCell[][] cells;
    protected Tile[][] tiles;
    protected int X, Y, scale, rotation;

    public Grid(int X, int Y, int scale, int rotation) {
        cells = new GridCell[X][Y];
        tiles = new Tile[X][Y];
        this.X = X;
        this.Y = Y;
        setScale(scale, rotation);
        allCells((x, y) -> {
            cells[x][y] = new GridCell();
            tiles[x][y] = makeTile(x, y);
        });
    }

    private void setScale(int scale, int rotation) {
        setBackground(Color.BLUE);
        this.scale = scale;
        this.rotation = rotation;
        setSize();
    }

    private void setSize(){
        setPreferredSize(dimensions());
        setMinimumSize(dimensions());
        setMaximumSize(dimensions());
    }

    public abstract Dimension dimensions();

    public abstract Tile makeTile(int x, int y);

    private void draw(Graphics map, int x, int y) {
        cells[x][y].draw(map, tiles[x][y], scale, rotation);
    }

    @Override
    protected void paintComponent(Graphics map) {
        super.paintComponent(map);
        allCells((x, y) -> draw(map, x, y));
    }

    private interface CoordFunc {
        public void apply(int x, int y);
    }

    private void allCells(CoordFunc func) {
        for (int x = 0; x < X; x++)
            for (int y = 0; y < Y; y++)
                func.apply(x, y);
    }

}
