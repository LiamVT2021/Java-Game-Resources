package grid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.ToIntFunction;

public class Entity {

    private final String logo;
    private final Color color;

    public Entity(String logo, Color color) {
        this.logo = logo;
        this.color = color;
    }

    public String logo() {
        return logo;
    }

    public Color color() {
        return color;
    }

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

}
