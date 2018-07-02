package by.issoft.environment.servo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ServoLogging implements Servo {
	private final Servo servo;
	private final Logger logger;

	public ServoLogging(final Servo servo, final Logger logger) {
		this.servo = servo;
		this.logger = logger;
	}

	public ServoLogging(final Servo servo) {
		this(servo, LoggerFactory.getLogger(servo.getClass()));
	}

	@Override
	public void rotate(final int angle) {
		servo.rotate(angle);
		logger.info("Servo is rotated to angle = " + angle + "°");
	}

	@Override
	public void push() {
		servo.push();
		logger.info("Servo is in a pushed state");
	}

	@Override
	public void pull() {
		servo.pull();
		logger.info("Servo is in a pulled state");
	}

	@Override
	public void pushAndPull() {
		servo.pushAndPull();
		logger.info("Servo was pushed and pulled");
	}

	@Override
	public void initialize() throws IOException, InterruptedException {
		logger.info("Servo's initialize starts");
		servo.initialize();
		logger.info("Servo's initialize was done");
	}
}