package grid;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class SquareGrid extends Grid {

    public SquareGrid(int X, int Y, int scale) {
        super(X, Y, scale);
    }

    @Override
    public int sides() {
        return 4;
    }

    @Override
    public void makePoly() {
        polyX = new int[] { -scale, scale, scale, -scale };
        polyY = new int[] { -scale, -scale, scale, scale };
        cellWidth = 2 * scale;
        cellHeight = 2 * scale;
    }

    @Override
    public int centerX(int x, int y) {
        return (x + 1) * cellWidth;
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

    @Override
    public String vector(int x, int y) {
        return "(" + x + ", " + -y + ")";
    }

    @Override
    public int distance(int x, int y) {
        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public Point clickLoc(MouseEvent e) {
        return new Point(fromClick(e.getX()), fromClick(e.getY()));
    }

    private int fromClick(int v) {
        int n = v / scale;
        return n <= 0 ? -1 : (n - 1) / 2;
    }

}
