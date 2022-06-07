package grid;

import java.awt.Graphics;
// import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class GridCell {

    private Color tileColor, borderColor;
    private int borderwidth;

    public GridCell() {
        tileColor = Color.PINK;
        borderColor = Color.BLACK;
        borderwidth = 3;
    }

    public void draw(Graphics graph, Tile tile, int scale, int rotation) {
        tile.drawTile(graph, tileColor, borderColor, new BasicStroke(borderwidth));
    }
}
