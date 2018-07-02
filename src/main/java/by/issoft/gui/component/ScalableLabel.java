package by.issoft.gui.component;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ScalableLabel extends JLabel {
	// create scaled label by text
	private ScalableLabel(TextAsLabelBuilder builder) {
		this.setText(builder.caption);
		this.setForeground(builder.foregroundColor);
		scaleLabelText(builder.width, builder.height);//scale text by width and height
	}

	// upload image from web and scale it
	private ScalableLabel(WebImageAsLabelBuilder builder) {
		try {
			final BufferedImage photo = ImageIO.read(builder.url);
			final BufferedImage scaledPhoto = Scalr.resize(photo, Scalr.Mode.FIT_TO_HEIGHT, builder.width, builder.height);
			this.setIcon(new ImageIcon(scaledPhoto));
			this.setSize(builder.width, builder.height);

			this.setVerticalTextPosition(SwingConstants.TOP);
			this.setHorizontalTextPosition(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.TOP);
			this.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
			this.setText("Can't upload image by URL='" + builder.url);
		}
	}

	// get image from resources and scale it
	private ScalableLabel(ResourceImageAsLabelBuilder builder) {
		Icon uploadedIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(builder.imagePath)));
		this.setIcon(uploadedIcon);
		this.setSize(builder.width, builder.height);

		this.setVerticalTextPosition(SwingConstants.TOP);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void scaleLabelText(final int width, final int height) {
		Font labelFont = this.getFont();
		final int stringWidth = this.getFontMetrics(labelFont).stringWidth(this.getText());
		final double widthRatio = (double) width / (double) stringWidth;
		final int newFontSize = (int) (labelFont.getSize() * widthRatio);
		this.setFont(new Font(labelFont.getName(), Font.PLAIN, Math.min(newFontSize, height)));
	}

	public static class TextAsLabelBuilder {
		private final String caption;
		private int width;
		private int height;
		private Color foregroundColor;

		public TextAsLabelBuilder(String caption) {
			this.caption = caption;
		}

		public TextAsLabelBuilder withWidth(int width) {
			this.width = width;
			return this;
		}

		public TextAsLabelBuilder withHeight(int height) {
			this.height = height;
			return this;
		}

		public TextAsLabelBuilder withColor(Color foregroundColor) {
			this.foregroundColor = foregroundColor;
			return this;
		}

		public ScalableLabel build() {
			return new ScalableLabel(this);
		}
	}

	public static class WebImageAsLabelBuilder {
		private URL url;
		private int width;
		private int height;

		public WebImageAsLabelBuilder(URL url) {
			this.url = url;
		}

		public WebImageAsLabelBuilder withWidth(int width) {
			this.width = width;
			return this;
		}

		public WebImageAsLabelBuilder withHeight(int height) {
			this.height = height;
			return this;
		}

		public ScalableLabel build() {
			return new ScalableLabel(this);
		}
	}

	public static class ResourceImageAsLabelBuilder {
		private String imagePath;
		private int width;
		private int height;

		public ResourceImageAsLabelBuilder(String imagePath) {
			this.imagePath = imagePath;
		}

		public ResourceImageAsLabelBuilder withWidth(int width) {
			this.width = width;
			return this;
		}

		public ResourceImageAsLabelBuilder withHeight(int height) {
			this.height = height;
			return this;
		}

		public ScalableLabel build() {
			return new ScalableLabel(this);
		}
	}
}