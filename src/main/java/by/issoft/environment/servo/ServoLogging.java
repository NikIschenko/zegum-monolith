package by.issoft.environment.servo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ServoLogging implements Servo {
	private final Servo servo;
	private final Logger logger;

	public ServoLogging(Servo servo, Logger logger) {
		this.servo = servo;
		this.logger = logger;
	}

	public ServoLogging(Servo servo) {
		this(servo, LoggerFactory.getLogger(servo.getClass()));
	}

	@Override
	public void rotate(int angle) {
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
	public void initialize() {
		logger.info("Servo's initialization starts");
		try {
			servo.initialize();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			logger.error("Servo can't be initialized");
		}
		logger.info("Servo's initialization was done");
	}
}