package grid;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;

import ui.SimpleAction;
import ui.SimpleWindow;

/**
 * Launcher for creating new Grids
 * 
 * @author Liam Snyder
 * @version 9/4/22
 */
public class DM_Launcher extends SimpleWindow {

    private static Font font = new Font(null, 0, 24);
    private static String WIDTH = "Width";
    private static String HEIGHT = "Height";
    private static String SCALE = "Scale";

    private final Map<String, JTextField> ints;
    private final JCheckBox hex;

    public DM_Launcher() {
        super(new JFrame());
        ints = new HashMap<>();
        window.setLayout(new GridLayout(0, 2));
        addIntRow(WIDTH, 13);
        addIntRow(HEIGHT, 7);
        addIntRow(SCALE, 40);
        hex = new JCheckBox("Hex");
        window.add(hex);
        window.add(new JButton(new SimpleAction("Create Grid", this::createGrid)));
        start();
    }

    /**
     * helper method for creating the input fields
     * 
     * @param str  the name of this field
     * @param base the default value for this field
     */
    private void addIntRow(String str, int base) {
        JLabel label = new JLabel(str);
        label.setFont(font);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        window.add(label);
        JTextField text = new JTextField();
        text.setText(String.valueOf(base));
        text.setFont(font);
        ints.put(str, text);
        window.add(text);
    }

    /**
     * @param key Width, Height, or Scale
     * @return the input value from the associated field
     */
    public int getValue(String key) {
        return Integer.valueOf(ints.get(key).getText());
    }

    /**
     * creates a new grid based on the input values
     */
    public void createGrid() {
        Grid.Constructor grid = hex.isSelected() ? HexGrid::new : SquareGrid::new;
        new GridWindow(grid.make(getValue(WIDTH), getValue(HEIGHT), getValue(SCALE)));
    }

    public static void main(String[] args) {
        new DM_Launcher();
    }
}
