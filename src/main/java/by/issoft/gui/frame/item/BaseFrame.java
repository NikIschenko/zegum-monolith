package by.issoft.gui.frame.item;

import by.issoft.ApplicationVariables;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {

    public BaseFrame() {
        this.setSize(ApplicationVariables.WIDHT, ApplicationVariables.HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        if (ApplicationVariables.FULL_SCREEN) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setUndecorated(true);
        }
    }
}
