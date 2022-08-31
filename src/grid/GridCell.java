package grid;

import java.awt.Graphics2D;
import java.util.function.ToIntFunction;
import java.awt.Color;

public class GridCell {

    private Color tileColor;
    private Actor actor;

    public GridCell() {
        tileColor = Color.PINK;
    }

    public void draw(Graphics2D graph, Tile tile, Color highlightColor,
            int radius, int height, ToIntFunction<String> width) {
        tile.drawTile(graph, tileColor(), highlightColor);
        if (actor != null)
            actor.draw(graph, tile, radius, height, width);
    }

    public Color tileColor() {
        return tileColor;
    }

    public void setTileColor(Color color) {
        tileColor = color;
    }

    public Actor setActor(Actor actor) {
        Actor old = this.actor;
        this.actor = actor;
        return old;
    }

}
