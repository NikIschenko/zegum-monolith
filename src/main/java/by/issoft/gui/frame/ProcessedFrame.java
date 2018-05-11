package by.issoft.gui.frame;

import by.issoft.environment.servo.Servo;
import by.issoft.service.recognition.Recognition;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.stream.IntStream;

public class ProcessedFrame extends FrameNode {
	private JLabel processedPhoto;
	private final Recognition recognition;
	private final Servo servo;

	public ProcessedFrame(final Servo servo, final Recognition recognition) {
		this.servo = servo;
		this.recognition = recognition;
		// -- listeners
		this.frame().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				showNextFrame();
			}
		});
		buildInterface();
	}

	private void buildInterface() {
		processedPhoto = new JLabel();
		final JPanel backgroundPanel = buildBackgroundPanel();
		backgroundPanel.add(processedPhoto);
		this.frame().add(backgroundPanel);
	}

	@Override
	void onShow() {
		IntStream.range(0, recognition.recognitionResult().smileCount())
				.forEach(s-> servo.pushAndPull());
		setProcessedPhotoByUrl(recognition.recognitionResult().processedImageUrl());
	}

	private void setProcessedPhotoByUrl(final String urlString) {
		try {
			final BufferedImage photo = ImageIO.read(new URL(urlString));
			final BufferedImage resizedPhoto = Scalr.resize(photo, Scalr.Mode.FIT_TO_HEIGHT, this.frame().getWidth(), this.frame().getHeight());
			processedPhoto.setIcon(new ImageIcon(resizedPhoto));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JPanel buildBackgroundPanel() {
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new GridBagLayout());
		backgroundPanel.setBackground(Color.BLACK);
		return backgroundPanel;
	}
}