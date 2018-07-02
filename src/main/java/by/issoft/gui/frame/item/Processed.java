package by.issoft.gui.frame.item;

import by.issoft.environment.servo.Servo;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.Navigator;
import by.issoft.service.RecognitionServiceImpl;
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

public class Processed extends BaseFrame implements Frame {
	private Navigator navigator;
	private Servo servo;

	private JPanel backgroundPanel;

	public Processed(Navigator navigator, Servo servo) {
		super();
		this.navigator = navigator;
		this.servo = servo;
	}

	public void createComponents() {
		backgroundPanel = new JPanel();
		this.add(backgroundPanel);
		// -- listeners
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				navigator.showNext();
			}
		});
	}

	@Override
	public void showFrame() {
		backgroundPanel.removeAll();
		JLabel processedImage = getScaledImage(RecognitionServiceImpl.lastRecognitionResult.getProcessedImageUrl(), new Dimension(this.getWidth(), this.getHeight()));
		backgroundPanel.add(processedImage);
		new Thread(()-> IntStream.range(0, RecognitionServiceImpl.lastRecognitionResult.getSmileCount())
				.forEach(s -> servo.pushAndPull())).start();
		this.setVisible(true);
	}

	private JLabel getScaledImage(String imageUrl, Dimension dimension) {
		JLabel processedImage = new JLabel();
		BufferedImage photo;
		try {
			photo = ImageIO.read(new URL(imageUrl));
		} catch (IOException e) {
			return processedImage;
		}
		BufferedImage resizedPhoto = Scalr.resize(photo, Scalr.Mode.FIT_TO_HEIGHT, dimension.width, dimension.height);
		processedImage.setIcon(new ImageIcon(resizedPhoto));
		processedImage.setSize(dimension.width, dimension.height);

		processedImage.setVerticalTextPosition(SwingConstants.TOP);
		processedImage.setHorizontalTextPosition(SwingConstants.CENTER);
		return processedImage;
	}

	@Override
	public void hideFrame() {
		this.setVisible(false);
	}
}