package grid;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Polygon;

public class HexGrid extends Grid {

    private int half;
    private int rt;
    private int w, h;
    private static final double rt3 = Math.sqrt(3);

    public HexGrid(int X, int Y, int scale) {
        super(X, Y, scale, 0);
    }

    @Override
    protected void setScale(int scale, int rotation) {
        super.setScale(scale, rotation);
        half = scale / 2;
        double root = rt3 * scale;
        rt = (int) (root / 2);
        w=rt;
        h = scale * 3 / 4;
    }

    @Override
    public Tile makeTile(int x, int y) {
        int cx = (2 * x + y) * w;
        int cy = 2 * y * h;
        int x1 = cx + rt;
        int x2 = cx - rt;
        int y1 = cy - scale;
        int y2 = cy - half;
        int y3 = cy + half;
        int y4 = cy + scale;
        return new Tile(cx, cy, new Polygon(new int[] { cx, x1, x1, cx, x2, x2 },
                new int[] { y1, y2, y3, y4, y3, y2 }, 6));
    }

    @Override
    public Dimension dimensions() {
        return new Dimension((2 * X + 1) * scale, (2 * Y + 1) * scale);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new HexGrid(10, 7, 60));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}
