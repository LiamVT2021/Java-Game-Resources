package grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.TextAction;

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
    private final Font font;
    private final ComboBoxModel<String> color;
    private final JTextField text;
    private JPanel opBar;

    private Color getColor() {
        return colors.get(color.getSelectedItem());
    }

    private JComboBox<String> newColor() {
        JComboBox<String> colorBox = new JComboBox<>(color);
        colorBox.setFont(font);
        return colorBox;
    }

    private String getText() {
        return text.getText();
    }

    public GridWindow(Grid grid) {
        window = new JFrame();
        this.grid = grid;
        window.setLayout(new BorderLayout());
        window.add(grid, BorderLayout.CENTER);

        font = new Font(null, 0, 24);

        color = new DefaultComboBoxModel<String>(colors.keySet().toArray(String[]::new));
        text = new JTextField(3);
        text.setFont(font);

        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        JMenu drawMenu = new JMenu("Draw");
        menuBar.add(drawMenu);
        JMenu actorMenu = new JMenu("Actor");
        menuBar.add(actorMenu);

        addBar(drawMenu, "Draw Color", new GridOperation.DrawShape(grid, () -> null, this::getColor,
                () -> (cell) -> cell.setTileColor(getColor())), newColor());
        addBar(actorMenu, "Add Actor", new GridOperation.Simple(grid,
                (cell) -> cell.setActor(new Actor(getText(), getColor()))), newColor(), text);
        addBar(actorMenu, "Remove Actor", new GridOperation.Simple(grid, (cell) -> cell.setActor(null)));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

    }

    private void addBar(JMenu menu, String str, GridOperation op, JComponent... components) {
        JPanel bar = null;
        if (components.length > 0) {
            bar = new JPanel();
            Arrays.stream(components).forEach(bar::add);
        }
        menu.add(new SelectOp(str, bar, op));
    }

    private class SelectOp extends TextAction {

        public SelectOp(String name, JPanel bar, GridOperation op) {
            super(name);
            select = () -> {
                if (opBar != null)
                    window.remove(opBar);
                opBar = bar;
                if (bar != null)
                    window.add(bar, BorderLayout.NORTH);
                grid.gridOp = op;
                System.out.println(name);
                window.validate();
            };
        }

        private Runnable select;

        @Override
        public void actionPerformed(ActionEvent e) {
            select.run();
        }

    }

    public static void main(String[] args) {
        GridWindow dm = new GridWindow(new HexGrid(9, 9, 50));
        dm.grid.cells[2][2].setActor(new Actor("LS", Color.GREEN));
        dm.grid.repaint();
    }

}
