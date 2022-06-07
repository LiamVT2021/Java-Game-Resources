package grid;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Polygon;

public class SquareGrid extends Grid {

    public SquareGrid(int X, int Y, int scale) {
        super(X, Y, scale, 0);
    }

    @Override
    public Tile makeTile(int x, int y) {
        int cx = (x + 1) * scale;
        int cy = (y + 1) * scale;
        int d = scale / 2;
        int x1 = cx - d;
        int x2 = cx + d;
        int y1 = cy - d;
        int y2 = cy + d;
        return new Tile(cx, cy, new Polygon(new int[] { x1, x2, x2, x1 }, new int[] { y1, y1, y2, y2 }, 4));
    }

    @Override
    public Dimension dimensions() {
        return new Dimension((X + 1) * scale, (Y + 1) * scale);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new SquareGrid(10, 7, 60));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}
