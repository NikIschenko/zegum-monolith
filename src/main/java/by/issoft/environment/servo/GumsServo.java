package by.issoft.environment.servo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static by.issoft.ApplicationVariables.*;

public class GumsServo implements Servo {
	private Logger log = LoggerFactory.getLogger(GumsServo.class);

	@Override
	public void initialize() {
		try {
			Runtime.getRuntime().exec("gpio mode " + PIN + " pwm").waitFor();
			Runtime.getRuntime().exec("gpio pwm-ms").waitFor();
			Runtime.getRuntime().exec("gpio pwmc 192").waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rotate(int angle) {
		try {
			Runtime.getRuntime().exec("gpio pwm " + PIN + " " + angle).waitFor();
			Thread.sleep(DELAY);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Servo is rotated to angle = " + angle + "°");
	}

	@Override
	public void push() {
		rotate(PUSH_ANGLE);
		log.info("Servo is in a pushed state");
	}

	@Override
	public void pull() {
		rotate(PULL_ANGLE);
		log.info("Servo is in a pulled state");
	}

	@Override
	public void pushAndPull() {
		push();
		try {
			Thread.sleep(DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pull();
		log.info("Servo was pushed and pulled");
	}
}