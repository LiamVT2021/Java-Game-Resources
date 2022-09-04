package grid;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public abstract class Grid extends JPanel {

    public static final BasicStroke stroke = new BasicStroke(2);

    protected GridCell[][] cells;
    protected Tile[][] tiles;
    protected GridOperation gridOp;
    protected int numColumns, numRows, cellWidth, cellHeight, scale, radius;
    protected int[] polyX, polyY, highX, highY;

    @FunctionalInterface
    public static interface Constructor {
        public Grid make(int width, int height, int scale);
    }

    public Grid(int width, int height, int scale) {
        setBackground(Color.LIGHT_GRAY);
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
        MouseInputListener clicker = new MouseInputListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (gridOp != null && gridOp.onClick(clickLoc(e)))
                    repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (gridOp != null && gridOp.onHover(clickLoc(e)))
                    repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        this.addMouseListener(clicker);
        this.addMouseMotionListener(clicker);
    }

    public void setScale(int scale) {
        this.scale = scale;
        radius = 7 * scale / 9;
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

    private void draw(Graphics2D map, int x, int y, int height, ToIntFunction<String> width) {
        cells[x][y].draw(map, tiles[x][y], gridOp != null ? gridOp.highlight(x, y) : null,
                radius, height, width);
    }

    @Override
    protected void paintComponent(Graphics map) {
        super.paintComponent(map);
        Graphics2D graph = (Graphics2D) map;
        Font font = new Font("", 0, radius);
        FontMetrics metrics = graph.getFontMetrics(font);
        graph.setStroke(Grid.stroke);
        graph.setFont(font);
        int height = (metrics.getAscent() - metrics.getDescent()) / 2;
        ToIntFunction<String> width = metrics::stringWidth;
        allCells((x, y) -> draw(graph, x, y, height, width));
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

    public abstract Point clickLoc(MouseEvent e);

    public boolean inbounds(int x, int y) {
        return x >= 0 && x < cells.length && y >= 0 && y < cells[0].length;
    }

}
