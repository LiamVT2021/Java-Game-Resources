package grid;

import javax.swing.JFrame;
import java.awt.Dimension;

public class HexGrid extends Grid {

    private int cut;
    private static final double rt3 = Math.sqrt(3) / 2;

    public HexGrid(int X, int Y, int scale) {
        super(X, Y, scale);
    }

    @Override
    public int sides() {
        return 6;
    }

    @Override
    public void makePoly() {
        cellWidth = (int) (rt3 * scale);
        cellHeight = scale * 3 / 2;
        cut = numRows / 2;
        int half = scale / 2;
        polyX = new int[] { 0, cellWidth, cellWidth, 0, -cellWidth, -cellWidth };
        polyY = new int[] { -scale, -half, half, scale, half, -half };
    }

    @Override
    public int centerX(int x, int y) {
        return (2 * x + y + 2 - cut) * cellWidth;
    }

    @Override
    public Dimension dimensions() {
        return new Dimension(2 * (numColumns + 1) * cellWidth, (numRows + 1) * cellHeight);
    }

    @Override
    protected void allCells(CoordFunc func) {
        for (int y = 0; y < numRows; y++) {
            int s = Math.max(cut - y, 0);
            int e = Math.min(numColumns + cut - y, numColumns);
            System.out.println(y + ',' + s + ',' + e);
            for (int x = s; x < e; x++)
                func.apply(x, y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new HexGrid(17, 11, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public String vector(int x, int y) {
        if (x == -y)
            return x == 0 ? "0" : String.valueOf(y) + "z";
        if (y == 0)
            return String.valueOf(x) + 'x';
        if (x == 0)
            return String.valueOf(-y) + 'y';
        if (x > 0) {
            if (y > 0)
                return String.valueOf(x) + "x - " + y + 'y';
            if (x > -y)
                return String.valueOf(x + y) + "x - " + -y + 'z';
            return String.valueOf(-x - y) + "y - " + x + 'z';
        } else {
            if (y < 0)
                return String.valueOf(-y) + "y - " + -x + 'x';
            if (y > -x)
                return String.valueOf(-x) + "z - " + (x + y) + 'y';
            return String.valueOf(y) + "z - " + (-x - y) + 'x';
        }
    }

    @Override
    public int distance(int x, int y) {
        return (Math.abs(x) + Math.abs(x + y) + Math.abs(y)) / 2;
    }

}
