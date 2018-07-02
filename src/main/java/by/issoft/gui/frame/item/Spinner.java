package by.issoft.gui.frame.item;

import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.Navigator;
import by.issoft.environment.camera.Camera;
import by.issoft.service.RecognitionServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Spinner extends BaseFrame implements Frame {
	private Navigator navigator;
	private Camera camera;

	public Spinner(Navigator navigator, Camera camera) {
		this.navigator = navigator;
		this.camera = camera;
	}

	public void createComponents() {
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new GridBagLayout());
		backgroundPanel.setBackground(Color.WHITE);
		backgroundPanel.add(getSpinnerLabel());
		this.add(backgroundPanel);
	}


	private JLabel getSpinnerLabel() {
		JLabel backgroundImage = new JLabel();
		Icon uploadedIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("spinner.gif")));
		backgroundImage.setIcon(uploadedIcon);
		backgroundImage.setSize(this.getWidth(), this.getHeight());
		backgroundImage.setVerticalTextPosition(SwingConstants.TOP);
		backgroundImage.setHorizontalTextPosition(SwingConstants.CENTER);
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setHorizontalAlignment(SwingConstants.CENTER);
		return backgroundImage;
	}

	private void sendToRecognition(BufferedImage photo) {
		new Thread(()->{
			RecognitionServiceImpl.uploadPhoto(photo);
			navigator.showNext();
		}).start();
	}

	@Override
	public void showFrame() {
		sendToRecognition(camera.photo());
		this.setVisible(true);
	}

	@Override
	public void hideFrame() {
		this.setVisible(false);
	}
}