package by.issoft.gui.frame.item;

import by.issoft.environment.camera.Camera;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.Navigator;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Greeting extends BaseFrame implements Frame {
	private Navigator navigator;
	private Camera camera;

	public Greeting(Navigator navigator, Camera camera) {
		super();
		this.navigator = navigator;
		this.camera = camera;
	}

	public void createComponents() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				navigator.showNext();
			}
		});
        WebcamPanel webcamPanel = new WebcamPanel(this.camera.webcam());

		webcamPanel.add(getGreetingLabel());
		this.add(webcamPanel);
	}

	private JLabel getGreetingLabel() {
		JLabel backgroundImage = new JLabel();
		Icon uploadedIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("greeting.png")));
		backgroundImage.setIcon(uploadedIcon);
		backgroundImage.setSize(this.getWidth(), this.getHeight());
		backgroundImage.setVerticalTextPosition(SwingConstants.TOP);
		backgroundImage.setHorizontalTextPosition(SwingConstants.CENTER);
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setHorizontalAlignment(SwingConstants.CENTER);
		return backgroundImage;
	}

	@Override
	public void showFrame() {
		this.setVisible(true);
	}

	@Override
	public void hideFrame() {
		this.setVisible(false);
	}
}