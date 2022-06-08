package grid;

import java.awt.Graphics;
// import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Stroke;

public class GridCell {

    Color tileColor, borderColor;
    private static final Stroke REG_STROKE = new BasicStroke(2);
    private static final Stroke BOLD_STROKE = new BasicStroke(4);

    public GridCell() {
        tileColor = Color.PINK;
        // borderColor = Color.BLACK;
    }

    public Color tileColor() {
        return tileColor;
    }

    public Color bordeColor() {
        return borderColor;
    }

    public void draw(Graphics graph, Tile tile, int scale, int rotation) {
        tile.drawTile(graph, this);
    }
}
