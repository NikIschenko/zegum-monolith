package by.issoft.gui.frame.item;

import by.issoft.environment.camera.Camera;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.Navigator;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class Countdown extends BaseFrame implements Frame {
	private Navigator navigator;
	private Camera camera;
	private Timer timer;

	private int countdownDuration = 3000;
	private int timeStep = 10;
	private String timeFormat = "s:SSS";
	private long startTime = -1;

	private JLabel countdownLabel;

	public Countdown(Navigator navigator, Camera camera) {
		super();
		this.navigator = navigator;
		this.camera = camera;
		this.timer = new Timer(timeStep, this.onTimerEvent());
	}

	public void createComponents() {
		countdownLabel = getScalabledLabel(new Dimension(200, 200));
		countdownLabel.setForeground(Color.RED);

		WebcamPanel webcamPanel = new WebcamPanel(camera.webcam());
		webcamPanel.add(countdownLabel);
		this.add(webcamPanel);
	}

	private JLabel getScalabledLabel(Dimension dimension) {
		JLabel label = new JLabel(timeFormat);
		label.setSize(new Dimension(200, 200));
		Font labelFont = label.getFont();
		String labelText = label.getText();
		int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = label.getWidth();
		double widthRatio = (double)componentWidth / (double)stringWidth;
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = label.getHeight();
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		return label;
	}

	private ActionListener onTimerEvent() {
		SimpleDateFormat timeFormat = new SimpleDateFormat(this.timeFormat);
		return e -> {
			long now = System.currentTimeMillis();
			long clockTime = now - startTime;
			countdownLabel.setText(timeFormat.format(countdownDuration - clockTime));
			if ((countdownDuration - clockTime) <= timeStep * 2) {
				countdownLabel.setText(timeFormat.format(0));
			}
			if (countdownDuration - clockTime < 0) {
				stopTimer();
			}
		};
	}

	private void stopTimer() {
		timer.stop();
		navigator.showNext();
	}

	@Override
	public void showFrame() {
		if (!timer.isRunning()) {
			startTime = System.currentTimeMillis();
			timer.start();
		}
		this.setVisible(true);
	}

	@Override
	public void hideFrame() {
		this.setVisible(false);
	}
}