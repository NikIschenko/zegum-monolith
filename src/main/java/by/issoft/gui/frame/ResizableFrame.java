package by.issoft.gui.frame;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;

public class ResizableFrame {
    private final Dimension size;
    private final boolean fullScreen;

    public ResizableFrame(@NotNull Dimension size, boolean fullScreen) {
        this.size = size;
        this.fullScreen = fullScreen;
    }

    public JFrame instance() {
        JFrame newFrame = new JFrame();
        newFrame.setSize(size);
        newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        if (fullScreen) {
            newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            newFrame.setUndecorated(true);
        }
        return newFrame;
    }
}
