package grid;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.BasicStroke;

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

    public void drawTile(Graphics map, Color tileColor, Color highLightColor) {
        Graphics2D graph = (Graphics2D) map;
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
        graph.setStroke(stroke);
        graph.drawPolygon(outer);
    }

    private static final BasicStroke stroke = new BasicStroke(2);

}
