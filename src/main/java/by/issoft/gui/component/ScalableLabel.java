package by.issoft.gui.component;

import javax.swing.*;
import java.awt.*;

public class ScalableLabel extends JLabel {
	public ScalableLabel(final String title) {
		super(title);
	}

	public void scaleToBoxSize(final int width, final int height) {
		Font labelFont = this.getFont();

		final int stringWidth = this.getFontMetrics(labelFont).stringWidth(this.getText());
		final double widthRatio = (double) width / (double) stringWidth;
		final int newFontSize = (int) (labelFont.getSize() * widthRatio);

		this.setFont(new Font(labelFont.getName(), Font.PLAIN, Math.min(newFontSize, height)));
	}
}