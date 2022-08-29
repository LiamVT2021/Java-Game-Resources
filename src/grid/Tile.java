package grid;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Tile extends Point {

    private final Polygon outer, inner;

    public Tile(int x, int y, Polygon outer, Polygon inner) {
        super(x, y);
        this.outer = outer;
        this.inner = inner;
    }

    public int sides() {
        return outer.npoints;
    }

    public Point getCorner(int index) {
        return new Point(outer.xpoints[index], outer.ypoints[index]);
    }

    public boolean contains(Point point) {
        return outer.contains(point);
    }

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
