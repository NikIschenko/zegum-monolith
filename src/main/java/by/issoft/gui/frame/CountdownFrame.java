package by.issoft.gui.frame;

import by.issoft.environment.Environment;
import by.issoft.environment.camera.Camera;
import by.issoft.gui.component.ScalableLabel;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class CountdownFrame extends FrameNode {
	private final int COUNTDOWN_DURATION = 3000;
	private final int TIME_STEP = 10;
	private long START_TIME = -1;
	private final String TIME_FORMAT = "s:SSS";

	private final Environment environment;
	private final Timer timer;

	private ScalableLabel countdownLabel;

	public CountdownFrame(final Environment environment) {
		super(environment.environmentType());
		this.environment = environment;
		this.timer = new Timer(TIME_STEP, this.onTimerEvent());
		buildInterface();
	}

	private void buildInterface() {
		countdownLabel = buildCountdownLabel();
		final WebcamPanel webcamPanel = new WebcamPanel(environment.camera().webcam());
		webcamPanel.add(countdownLabel);
		this.frame().add(webcamPanel);
	}

	@Override
	void onShow() {
		if (!timer.isRunning()) {
			START_TIME = System.currentTimeMillis();
			timer.start();
		}
	}

	private ActionListener onTimerEvent() {
		SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
		return e -> {
			long now = System.currentTimeMillis();
			long clockTime = now - START_TIME;
			countdownLabel.setText(timeFormat.format(COUNTDOWN_DURATION - clockTime));

			if ((COUNTDOWN_DURATION - clockTime) <= TIME_STEP*2) {
				countdownLabel.setText(timeFormat.format(0));
			}

			if (COUNTDOWN_DURATION - clockTime < 0) {
				stopTimer();
			}
		};
	}

	private void stopTimer() {
		timer.stop();
		showNextFrame();
	}

	private ScalableLabel buildCountdownLabel() {
		ScalableLabel label = new ScalableLabel(TIME_FORMAT);
		label.scaleToBoxSize(200, 200);
		label.setForeground(Color.RED);
		return label;
	}
}