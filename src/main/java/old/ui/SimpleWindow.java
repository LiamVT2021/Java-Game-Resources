package old.ui;

import javax.swing.JFrame;

public class SimpleWindow {

    protected final JFrame window;

    public SimpleWindow(JFrame window) {
        this.window = window;
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void start() {
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
    }

}
