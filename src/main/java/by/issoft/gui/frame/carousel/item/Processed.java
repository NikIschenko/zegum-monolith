package by.issoft.gui.frame.carousel.item;

import by.issoft.environment.servo.Servo;
import by.issoft.gui.component.ScalableLabel;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.ResizableFrame;
import by.issoft.service.recognition.Recognition;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.IntStream;

public class Processed extends BaseCarouselItem implements Frame {
	private final JFrame jFrame;
	private final Recognition recognition;
	private final Servo servo;

	private JPanel backgroundPanel;

	public Processed(final ResizableFrame resizableFrame, final Servo servo, final Recognition recognition) {
		this.jFrame = resizableFrame.instance();
		this.servo = servo;
		this.recognition = recognition;
	}

	public void createComponents() {
		backgroundPanel = new JPanel();
		jFrame.add(backgroundPanel);
		// -- listeners
		jFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				((Frame)nextFrame).show();
				jFrame.setVisible(false);
			}
		});
	}

	@Override
	public void show() {
		try {
			backgroundPanel.removeAll();
			backgroundPanel.add(new ScalableLabel.WebImageAsLabelBuilder(new URL(recognition.recognitionResult().processedImageUrl()))
					.withWidth(jFrame.getWidth())
					.withHeight(jFrame.getHeight())
					.build());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		new Thread(()-> IntStream.range(0, recognition.recognitionResult().smileCount())
				.forEach(s -> servo.pushAndPull())).start();
		jFrame.setVisible(true);
	}
}