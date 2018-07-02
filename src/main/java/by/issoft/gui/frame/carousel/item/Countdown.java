package by.issoft.gui.frame.carousel.item;

import by.issoft.environment.camera.Camera;
import by.issoft.gui.component.ScalableLabel;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.ResizableFrame;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class Countdown extends BaseCarouselItem implements Frame {
	private final JFrame jFrame;
	private final Camera camera;
	private final Timer timer;

	private final int countdownDuration = 3000;
	private final int timeStep = 10;
	private final String timeFormat = "s:SSS";
	private long startTime = -1;

	private ScalableLabel countdownLabel;

	public Countdown(final ResizableFrame resizableFrame, final Camera camera) {
		this.jFrame = resizableFrame.instance();
		this.camera = camera;
		this.timer = new Timer(timeStep, this.onTimerEvent());
	}

	public void createComponents() {
		countdownLabel = new ScalableLabel.TextAsLabelBuilder(timeFormat)
				.withColor(Color.RED)
				.withWidth(200)
				.withHeight(200)
				.build();

		// new ScalableLabel(timeFormat, 200, 200, Color.RED);
		final WebcamPanel webcamPanel = new WebcamPanel(camera.webcam());
		webcamPanel.add(countdownLabel);
		jFrame.add(webcamPanel);
	}


	private ActionListener onTimerEvent() {
		SimpleDateFormat timeFormat = new SimpleDateFormat(this.timeFormat);
		return e -> {
			long now = System.currentTimeMillis();
			long clockTime = now - startTime;
			countdownLabel.setText(timeFormat.format(countdownDuration - clockTime));
			if ((countdownDuration - clockTime) <= timeStep*2) {
				countdownLabel.setText(timeFormat.format(0));
			}
			if (countdownDuration - clockTime < 0) {
				stopTimer();
			}
		};
	}

	private void stopTimer() {
		timer.stop();
		((Frame) nextFrame).show();
		jFrame.setVisible(false);
	}

	@Override
	public void show() {
		if (!timer.isRunning()) {
			startTime = System.currentTimeMillis();
			timer.start();
		}
		jFrame.setVisible(true);
	}
}