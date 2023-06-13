package grid;

import java.awt.Graphics2D;
import java.util.function.ToIntFunction;
import java.awt.Color;

/**
 * class representing the data stored in a grid cell
 * 
 * @author Liam Snyder
 * @version 9/4/22
 */
public class GridCell {

    private Color tileColor;
    private Actor actor;

    public GridCell() {
        tileColor = Color.WHITE;
    }

    /**
     * draws the cell on a graphics object
     * 
     * @param graph          the graphics object for the grid
     * @param tile           tile representing shape and location of cell
     * @param highlightColor color of highlight around tile
     * @param radius         actor radius
     * @param height         text height
     * @param width          text width function
     */
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

    public Actor getActor() {
        return actor;
    }

    public Actor setActor(Actor actor) {
        Actor old = this.actor;
        this.actor = actor;
        return old;
    }

}
