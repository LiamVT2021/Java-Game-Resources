package ui;

import java.awt.event.ActionEvent;

import javax.swing.text.TextAction;

public class SimpleAction extends TextAction {

    private final Runnable func;

    public SimpleAction(String name, Runnable method) {
        super(name);
        func = method;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        func.run();        
    }
    
}
