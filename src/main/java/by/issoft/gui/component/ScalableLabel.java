package by.issoft.gui.component;

import javax.swing.*;
import java.awt.*;

public class ScalableLabel extends JLabel {
	public ScalableLabel(String title) {
		super(title);
	}

	public void scaleToBoxSize(int width, int height) {
		Font labelFont = this.getFont();
		int stringWidth = this.getFontMetrics(labelFont).stringWidth(this.getText());
		double widthRatio = (double) width / (double) stringWidth;
		int newFontSize = (int) (labelFont.getSize() * widthRatio);
		int fontSizeToUse = Math.min(newFontSize, height);
		this.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
	}
}