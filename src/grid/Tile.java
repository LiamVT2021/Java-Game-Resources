package grid;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Color;
import java.awt.Polygon;

public class Tile extends Point {

    private final Polygon tile;

    public Tile(int x, int y, Polygon tile){
        super(x,y);
        this.tile = tile;
    }

    public int sides() {
        return tile.npoints;
    }

    public Point getCorner(int index) {
        return new Point(tile.xpoints[index], tile.ypoints[index]);
    }

    public boolean contains(Point point){
        return tile.contains(point);
    }

    public void drawTile(Graphics map, Color fill, Color border, Stroke stroke){
        Graphics2D graph = (Graphics2D) map;
        graph.setColor(fill);
        graph.fillPolygon(tile);
        graph.setColor(border);
        graph.setStroke(stroke);
        graph.drawPolygon(tile);
    }

}
