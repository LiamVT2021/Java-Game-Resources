package grid;

import java.awt.Graphics;
import java.awt.Color;
public class GridCell {

    Color tileColor, borderColor;

    public GridCell() {
        tileColor = Color.PINK;
    }

    public Color tileColor() {
        return tileColor;
    }

    public void draw(Graphics graph, Tile tile, int scale) {
        tile.drawTile(graph, this);
    }
}
