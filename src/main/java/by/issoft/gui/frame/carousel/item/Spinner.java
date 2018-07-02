package by.issoft.gui.frame.carousel.item;

import by.issoft.gui.component.ScalableLabel;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.ResizableFrame;
import by.issoft.service.recognition.Recognition;
import by.issoft.environment.camera.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spinner  extends BaseCarouselItem implements Frame {
	private final JFrame jFrame;
	private final Camera camera;
	private final Recognition recognition;

	public Spinner(final ResizableFrame resizableFrame, final Camera camera, final Recognition recognition) {
		this.jFrame = resizableFrame.instance();
		this.camera = camera;
		this.recognition = recognition;
	}

	public void createComponents() {
		final JPanel backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new GridBagLayout());
		backgroundPanel.setBackground(Color.WHITE);

		final JLabel spinnerComponent = new ScalableLabel
				.ResourceImageAsLabelBuilder("spinner.gif")
				.withWidth(jFrame.getWidth())
				.withHeight(jFrame.getHeight())
				.build();
		backgroundPanel.add(spinnerComponent);

		jFrame.add(backgroundPanel);
	}

	private void sendToRecognition(BufferedImage photo) {
		new Thread(()->{
			try {
				recognition.uploadPhoto(photo);
			} catch (IOException e) {
				e.printStackTrace();
			}
			((Frame)nextFrame).show();
			jFrame.setVisible(false);
		}).start();
	}

	@Override
	public void show() {
		sendToRecognition(camera.photo());
		jFrame.setVisible(true);
	}
}