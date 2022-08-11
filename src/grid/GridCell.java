package grid;

import java.awt.Graphics;
import java.awt.Color;

public class GridCell {

    private Color tileColor;

    public GridCell() {
        tileColor = Color.PINK;
    }

    public void draw(Graphics graph, Tile tile, int scale, Color highlightColor) {
        tile.drawTile(graph, tileColor(), highlightColor);
    }

    public Color tileColor() {
        return tileColor;
    }

    public void setTileColor(Color color) {
        tileColor = color;
    }

}
