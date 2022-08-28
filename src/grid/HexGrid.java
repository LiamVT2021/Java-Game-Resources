package grid;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class HexGrid extends Grid {

    private int cut;
    private static final double rt3 = 2.0 / Math.sqrt(3);

    public HexGrid(int X, int Y, int scale) {
        super(X, Y, scale);
    }

    @Override
    public int sides() {
        return 6;
    }

    @Override
    public void makePoly() {
        cut = numRows / 2;
        cellWidth = scale;
        int height = (int) (rt3 * scale);
        cellHeight = height * 3 / 2;
        int half = height / 2;
        polyX = new int[] { 0, cellWidth, cellWidth, 0, -cellWidth, -cellWidth };
        polyY = new int[] { -height, -half, half, height, half, -half };
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
            // System.out.println(y + ',' + s + ',' + e);
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
    public boolean inbounds(int x, int y) {
        return super.inbounds(x, y) && x + y >= cut && x + y < numColumns + cut;
    }

    @Override
    public int distance(int x, int y) {
        return (Math.abs(x) + Math.abs(x + y) + Math.abs(y)) / 2;
    }

    @Override
    public Point clickLoc(MouseEvent e) {
        double q = (double) e.getX() / cellWidth / 2
                - (double) e.getY() / cellHeight / 2
                + cells[0].length / 4. - .75;
        double r = (double) e.getY() / cellHeight - 1;
        // System.out.println("q: " + q + " r: " + r);
        return new Point(hexRound(q, r));
    }

    private Point hexRound(double x, double y) {
        double z = -x - y;
        int q = (int) Math.round(x);
        int r = (int) Math.round(y);
        int s = (int) Math.round(z);

        double q_diff = Math.abs(q - x);
        double r_diff = Math.abs(r - y);
        double s_diff = Math.abs(s - z);

        if (q_diff > r_diff && q_diff > s_diff)
            q = -r - s;
        else if (r_diff > s_diff)
            r = -q - s;
        // System.out.println("q: " + q + " r: " + r);
        return new Point(q, r);
    }

}
