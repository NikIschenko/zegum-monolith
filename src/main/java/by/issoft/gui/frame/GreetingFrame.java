package by.issoft.gui.frame;

import by.issoft.environment.camera.Camera;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class GreetingFrame extends FrameNode {
	private final Camera camera;

	public GreetingFrame(final Camera camera) {
		this.camera = camera;
		// show CountDown frame on click on screen
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
		final WebcamPanel webcamPanel = buildWebcamPanel(camera.camera());
		webcamPanel.add(buildGreetingLabel());
		this.frame().add(webcamPanel);
	}

	@Override
	void onShow() {
		// nothing
	}

	private WebcamPanel buildWebcamPanel(final com.github.sarxos.webcam.Webcam webcam) {
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setFPSDisplayed(false);
		webcamPanel.setDisplayDebugInfo(false);
		webcamPanel.setImageSizeDisplayed(false);
		webcamPanel.setMirrored(false);
		return webcamPanel;
	}

	private JLabel buildGreetingLabel() {
		Icon greetingImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("greeting.png")));
		JLabel greetingLabel = new JLabel(greetingImage);
		greetingLabel.setSize(this.getPayload().getWidth(), this.getPayload().getHeight());
		greetingLabel.setVerticalTextPosition(SwingConstants.TOP);
		greetingLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		greetingLabel.setVerticalAlignment(SwingConstants.TOP);
		greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return greetingLabel;
	}
}