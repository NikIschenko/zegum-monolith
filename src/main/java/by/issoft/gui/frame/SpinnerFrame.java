package by.issoft.gui.frame;

import by.issoft.service.recognition.Recognition;
import by.issoft.environment.camera.Camera;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class SpinnerFrame extends FrameNode {
	private final Camera camera;
	private final Recognition recognition;

	public SpinnerFrame(Camera camera, Recognition recognition) {
		this.camera = camera;
		this.recognition = recognition;
		buildInterface();
	}

	private void buildInterface() {
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new GridBagLayout());
		backgroundPanel.setBackground(Color.WHITE);

		JLabel spinnerComponent = buildSpinnerComponent();
		backgroundPanel.add(spinnerComponent);

		this.frame().add(backgroundPanel);
	}

	@Override
	void onShow() {
		sendToRecognition(camera.photo());
	}

	private void sendToRecognition(BufferedImage photo) {
		Thread uploadThread = new Thread(()->{
			recognition.uploadPhoto(bufferedImageToByteArray(photo));
			showNextFrame();
		});
		uploadThread.start();
	}

	private byte[] bufferedImageToByteArray(BufferedImage photo) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(photo, "jpg", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	private JLabel buildSpinnerComponent() {
		Icon spinnerImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("spinner.gif")));
		return new JLabel(spinnerImage);
	}
}