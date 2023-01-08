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
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * Grid class for storing and displaying all grid data
 * 
 * @author Liam Snyder
 * @version 9/4/22
 */
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

    /**
     * @param width  the number of columns wide
     * @param height the number of rows high
     * @param scale  the inner radial distance of the tiles
     */
    public Grid(int width, int height, int scale) {
        setBackground(Color.LIGHT_GRAY);
        cells = new GridCell[width][height];
        tiles = new Tile[width][height];
        highX = new int[sides()];
        highY = new int[sides()];
        this.numColumns = width;
        this.numRows = height;
        setScale(scale);
        allCells((q, r) -> {
            cells[q][r] = new GridCell();
            tiles[q][r] = makeTile(q, r);
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

    /**
     * sets the default polygon the tiles are constructed from
     */
    public abstract void makePoly();

    /**
     * @return the number of sides of the tiles
     */
    public abstract int sides();

    /**
     * @param q the column of the tile
     * @param r the row of the tile
     * @return a new tile representing the row and column,
     *         tiles need to be recreated when any dimensions change
     */
    public Tile makeTile(int q, int r) {
        int centerX = centerX(q, r);
        int centerY = (r + 1) * cellHeight;
        Polygon outer = new Polygon();
        Polygon inner = new Polygon();
        for (int i = 0; i < sides(); i++) {
            outer.addPoint(polyX[i] + centerX, polyY[i] + centerY);
            inner.addPoint(highX[i] + centerX, highY[i] + centerY);
        }
        return new Tile(centerX, centerY, outer, inner);
    }

    /**
     * @param q the column of the tile
     * @param r the row of the tile
     * @return the x pixel to center the tile
     */
    protected abstract int centerX(int q, int r);

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
        allCells((q, r) -> cells[q][r].draw(graph, tiles[q][r], gridOp != null ? gridOp.highlight(q, r) : null,
                radius, height, width));
    }

    /**
     * performs an operation on all cells in the grid
     * 
     * @param func the operation
     */
    protected void allCells(BiConsumer<Integer, Integer> func) {
        for (int r = 0; r < numRows; r++)
            for (int q = 0; q < numColumns; q++)
                func.accept(q, r);
    }

    /**
     * performs an operation on all cells in the grid
     * 
     * @param func the operation
     */
    protected void allCells(Consumer<GridCell> func) {
        Stream.of(cells).parallel().forEach(col -> Stream.of(col).filter(c -> c != null).forEach(func));
    }

    /**
     * @param q the column of the cell
     * @param r the row of the cell
     * @return vector from the center of the grid
     */
    public String coords(int q, int r) {
        return vector(q - numColumns / 2, r - numRows / 2);
    }

    /**
     * @param q the diffrence in columns
     * @param r the diffrence in rows
     * @return a string representing the vector
     */
    public abstract String vector(int q, int r);

    /**
     * @param q the diffrence in columns
     * @param r the diffrence in rows
     * @return the distance between the cells
     */
    public abstract int distance(int q, int r);

    public int distance(int q1, int r1, int q2, int r2) {
        return distance(q2 - q1, r2 - r1);
    }

    /**
     * @param e the mouse event
     * @return the q, r grid coordinates of the mouse
     */
    public abstract Point clickLoc(MouseEvent e);

    /**
     * @param q the column of the cell
     * @param r the row of the cell
     * @return if the coordinates are inside the grid
     */
    public boolean inbounds(int q, int r) {
        return q >= 0 && q < cells.length && r >= 0 && r < cells[0].length;
    }

    public abstract Map<String, Shape.Builder> shapes();

    public Shape.Builder getShape(String key) {
        return shapes().get(key);
    }

}
