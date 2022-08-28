package grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.awt.Color.*;

public class GridWindow {

    private static final Map<String, Color> colors = new LinkedHashMap<>();
    static {
        colors.put("Blue", BLUE);
        colors.put("Cyan", CYAN);
        colors.put("Green", GREEN);
        colors.put("Yellow", YELLOW);
        colors.put("Orange", ORANGE);
        colors.put("Red", RED);
        colors.put("Pink", PINK);
        colors.put("Magenta", MAGENTA);
        colors.put("White", WHITE);
        colors.put("Black", BLACK);
        colors.put("Gray", GRAY);
        colors.put("Light Gray", LIGHT_GRAY);
        colors.put("Dark Gray", DARK_GRAY);
    }

    private final JFrame window;
    private final Grid grid;
    private final JPanel drawBar;
    private final JComboBox<Object> color;
    private final GridOperation drawOp;

    private Color getColor() {
        return colors.get(color.getSelectedItem());
    }

    public GridWindow(Grid grid) {
        window = new JFrame();
        this.grid = grid;
        drawBar = new JPanel();
        color = new JComboBox<Object>(colors.keySet().toArray());
        drawBar.add(color);
        window.setLayout(new BorderLayout());
        window.add(drawBar, BorderLayout.NORTH);
        window.add(grid, BorderLayout.CENTER);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

        drawOp = new GridOperation.DrawShape(grid, () -> null, this::getColor,
                () -> (cell) -> cell.setTileColor(getColor()));
        grid.gridOp = drawOp;
    }

    public static void main(String[] args) {
        GridWindow dm = new GridWindow(new HexGrid(5, 5, 50));
    }

}
