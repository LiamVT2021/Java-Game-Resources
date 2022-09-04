package grid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.ToIntFunction;

/**
 * class representing an character on the grid
 * 
 * @author Liam Snyder
 * @version 9/4/22
 */
public class Actor {

    private final String logo;
    private final Color color;

    public Actor(String logo, Color color) {
        this.logo = logo;
        this.color = color;
    }

    public String logo() {
        return logo;
    }

    public Color color() {
        return color;
    }

    /**
     * draws the actor on a graphics object
     * 
     * @param graph  the graphics object for the grid
     * @param tile   tile with location of cell
     * @param radius radius of the circle
     * @param height text height
     * @param width  text width function
     */
    public void draw(Graphics2D graph, Tile tile, int radius, int height, ToIntFunction<String> width) {
        int x = tile.x - radius;
        int y = tile.y - radius;
        int d = radius * 2;
        graph.setColor(color());
        graph.fillOval(x, y, d, d);
        graph.setColor(Color.BLACK);
        graph.drawOval(x, y, d, d);
        String text = logo();
        graph.drawString(text, tile.x - width.applyAsInt(text) / 2, tile.y + height);
    }

    /**
     * add number field for generic npcs
     */
    public static class Generic extends Actor {
        private final int num;

        public Generic(String logo, Color color, int num) {
            super(logo, color);
            this.num = num;
        }

        public String name() {
            return super.logo();
        }

        @Override
        public String logo() {
            return super.logo() + num;
        }

    }

}
