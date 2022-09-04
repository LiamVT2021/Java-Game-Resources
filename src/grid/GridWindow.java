package grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import grid.Actor.Generic;
import ui.SimpleAction;
import ui.SimpleWindow;
import util.GenericList;

import static java.awt.Color.*;

public class GridWindow extends SimpleWindow {

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

    private final Grid grid;
    private final Font font = new Font(null, 0, 24);
    private final ComboBoxModel<String> color = new DefaultComboBoxModel<String>(
            colors.keySet().toArray(String[]::new));
    private final Map<Color, Map<String, GenericList<Actor>>> generics = new HashMap<>();
    private JPanel opBar;

    private Color getColor() {
        return colors.get(color.getSelectedItem());
    }

    private JComboBox<String> newColor() {
        JComboBox<String> colorBox = new JComboBox<>(color);
        return colorBox;
    }

    private Actor newGeneric(String name, Color color) {
        generics.putIfAbsent(color, new HashMap<>());
        generics.get(color).putIfAbsent(name, new GenericList<>());
        GenericList<Actor> list = generics.get(color).get(name);
        Actor actor = new Actor.Generic(name, color, list.next());
        list.add(actor);
        return actor;
    }

    private void remove(GridCell cell) {
        Actor actor = cell.setActor(null);
        if (actor instanceof Actor.Generic) {
            GenericList<Actor> list = generics.get(actor.color()).get(((Generic) actor).name());
            list.remove(actor);
            if (list.isEmpty())
                generics.get(actor.color()).remove(((Generic) actor).name());
        }
    }

    public GridWindow(Grid grid) {
        super(new JFrame());
        this.grid = grid;
        window.setLayout(new BorderLayout());
        window.add(grid, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
        JMenu drawMenu = new JMenu("Draw");
        menuBar.add(drawMenu);
        JMenu actorMenu = new JMenu("Actor");
        menuBar.add(actorMenu);

        addBar(drawMenu, "Draw Color", new GridOperation.DrawShape(grid, () -> null, this::getColor,
                () -> (cell) -> cell.setTileColor(getColor())), newColor());

        JTextField addUnique = new JTextField(4);
        JTextField addGeneric = new JTextField(3);
        addBar(actorMenu, "Add Unique",
                new GridOperation.Add(grid, () -> new Actor(addUnique.getText(), getColor()), this::getColor),
                newColor(), addUnique);
        addBar(actorMenu, "Add Generic",
                new GridOperation.Add(grid, () -> newGeneric(addGeneric.getText(), getColor()), this::getColor),
                newColor(), addGeneric);

        JLabel moveLogo = new JLabel("  ");
        moveLogo.setBorder(new EmptyBorder(10, 10, 10, 10));
        addBar(actorMenu, "Move Actor", new GridOperation.Move(grid, moveLogo), moveLogo);
        addBar(actorMenu, "Remove Actor", new GridOperation.Simple(grid, this::remove));

        start();
    }

    private void addBar(JMenu menu, String str, GridOperation op, JComponent... components) {
        JPanel bar = components.length > 0 ? new JPanel() : null;
        if (bar != null)
            Arrays.stream(components).forEach(comp -> {
                comp.setFont(font);
                bar.add(comp);
            });
        menu.add(new SimpleAction(str, () -> {
            if (opBar != null)
                window.remove(opBar);
            opBar = bar;
            if (bar != null)
                window.add(bar, BorderLayout.NORTH);
            grid.gridOp = op;
            System.out.println(str);
            window.pack();
        }));
    }

    public static void main(String[] args) {
        GridWindow dm = new GridWindow(new HexGrid(5, 5, 50));
        dm.grid.cells[2][2].setActor(new Actor("LS", Color.GREEN));
        dm.grid.repaint();
    }

}
