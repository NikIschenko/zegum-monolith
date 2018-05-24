package by.issoft.gui.frame;

// const
public class FrameSize {
    private final int width;
    private final int height;
    private final boolean fullScreen;

    public FrameSize(final int width, final int height, final boolean fullScreen) {
        this.width = width;
        this.height = height;
        this.fullScreen = fullScreen;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public boolean fullScreen() {
        return fullScreen;
    }
}
