package grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComboBox;
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
    private final JComboBox<Object> color;
    private final JTextField text;
    private JPanel opBar;

    private Color getColor() {
        return colors.get(color.getSelectedItem());
    }

    private String getText() {
        return text.getText();
    }

    public GridWindow(Grid grid) {
        window = new JFrame();
        this.grid = grid;
        window.setLayout(new BorderLayout());
        window.add(grid, BorderLayout.CENTER);

        Font font = new Font(null, 0, 24);

        color = new JComboBox<Object>(colors.keySet().toArray());
        color.setFont(font);
        text = new JTextField(3);
        text.setFont(font);

        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        JMenu drawMenu = new JMenu("Draw");
        menuBar.add(drawMenu);
        JMenu entityMenu = new JMenu("Entity");
        menuBar.add(entityMenu);

        JPanel drawBar = new JPanel();
        drawBar.add(color);
        addBar(drawMenu, "Draw Color", drawBar,
                new GridOperation.DrawShape(grid, () -> null, this::getColor,
                        () -> (cell) -> cell.setTileColor(getColor())));

        JPanel addEntity = new JPanel();
        addEntity.add(color);
        addEntity.add(text);
        addBar(entityMenu, "Add Entity", addEntity,
                new GridOperation.Simple(grid, (cell) -> cell.seEntity(new Entity(getText(), getColor()))));

        JPanel removeEntity = new JPanel();
        addBar(entityMenu, "Remove Entity", removeEntity,
                new GridOperation.Simple(grid, (cell) -> cell.seEntity(null)));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

    }

    private void addBar(JMenu menu, String str, JPanel bar, GridOperation op) {
        menu.add(new SelectOp(str, bar, op));
    }

    private class SelectOp extends TextAction {

        public SelectOp(String name, JPanel bar, GridOperation op) {
            super(name);
            select = () -> {
                if (opBar != null)
                    window.remove(opBar);
                opBar = bar;
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
        dm.grid.cells[2][2].seEntity(new Entity("LS", Color.GREEN));
        dm.grid.repaint();
    }

}
