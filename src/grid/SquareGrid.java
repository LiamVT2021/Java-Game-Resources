package grid;

import javax.swing.JFrame;
import java.awt.Dimension;

public class SquareGrid extends Grid {

    public SquareGrid(int X, int Y, int scale) {
        super(X, Y, scale, 0);
    }

    @Override
    public int sides() {
        return 4;
    }

    @Override
    public void makePoly() {
        polyX = new int[] { -scale, scale, scale, -scale };
        polyY = new int[] { -scale, -scale, scale, scale };
        super.makePoly();
    }

    @Override
    public Tile makeTile(int x, int y) {
        return super.makeTile((x + 1) * scale * 2, (y + 1) * scale * 2);
    }

    @Override
    public Dimension dimensions() {
        return new Dimension((X + 1) * scale * 2, (Y + 1) * scale * 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new SquareGrid(10, 7, 30));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}
