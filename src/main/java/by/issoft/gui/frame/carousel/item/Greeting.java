package by.issoft.gui.frame.carousel.item;

import by.issoft.environment.camera.Camera;
import by.issoft.gui.component.ScalableLabel;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.ResizableFrame;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Greeting extends BaseCarouselItem implements Frame {
	private final JFrame jFrame;
	private final Camera camera;

	public Greeting(final ResizableFrame resizableFrame, final Camera camera) {
		this.jFrame = resizableFrame.instance();
		this.camera = camera;
	}

	public void createComponents() {
		jFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				((Frame)nextFrame).show();
				jFrame.setVisible(false);
			}
		});

        final WebcamPanel webcamPanel = new WebcamPanel(this.camera.webcam());
        JLabel backgroundImage = new ScalableLabel.ResourceImageAsLabelBuilder("greeting.png")
				.withWidth(jFrame.getWidth())
				.withHeight(jFrame.getHeight())
				.build();
		webcamPanel.add(backgroundImage);

		jFrame.add(webcamPanel);
	}

	@Override
	public void show() {
		jFrame.setVisible(true);
	}
}