package grid;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * class representing a single tile on a grid
 * 
 * @author Liam Snyder
 * @version 9/4/22
 */
public class Tile extends Point {

    private final Polygon outer, inner;

    /**
     * @param x     the x coord of the center of the tile
     * @param y     the y coord of the center of the tile
     * @param outer polygon of the whole tile
     * @param inner smaller polygon used during highlight
     */
    public Tile(int x, int y, Polygon outer, Polygon inner) {
        super(x, y);
        this.outer = outer;
        this.inner = inner;
    }

    /**
     * @return the number of sides the polygon has
     */
    public int sides() {
        return outer.npoints;
    }

    /**
     * @param index index of the corner in the Polygon array
     * @return the pixel coordinates of the corner
     */
    public Point getCorner(int index) {
        return new Point(outer.xpoints[index], outer.ypoints[index]);
    }

    /**
     * @param point pixel coordinates on the grid
     * @return if the point is inside of the tile
     */
    public boolean contains(Point point) {
        return outer.contains(point);
    }

    /**
     * draws the tile on a graphics object
     * 
     * @param graph          the graphics object for the grid
     * @param tileColor      the color of the tile
     * @param highLightColor the color of the highlight around the tile
     */
    public void drawTile(Graphics2D graph, Color tileColor, Color highLightColor) {
        if (highLightColor != null) {
            graph.setColor(highLightColor);
            graph.fillPolygon(outer);
            graph.setColor(tileColor);
            graph.fillPolygon(inner);
        } else {
            graph.setColor(tileColor);
            graph.fillPolygon(outer);
        }
        graph.setColor(Color.BLACK);
        graph.drawPolygon(outer);
    }

}
