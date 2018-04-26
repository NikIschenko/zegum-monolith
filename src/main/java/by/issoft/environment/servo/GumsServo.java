package by.issoft.environment.servo;

import java.io.IOException;

public class GumsServo implements Servo {
	private final ServoParameters servoParameters;

	public GumsServo(ServoParameters servoParameters) {
		this.servoParameters = servoParameters;
	}

	@Override
	public void initialize() {
		try {
			Runtime.getRuntime().exec("gpio mode " + servoParameters.pin() + " pwm").waitFor();
			Runtime.getRuntime().exec("gpio pwm-ms").waitFor();
			Runtime.getRuntime().exec("gpio pwmc 192").waitFor();
			Runtime.getRuntime().exec("gpio pwmr 2000").waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rotate(int angle) {
		try {
			Runtime.getRuntime().exec("gpio pwm " + servoParameters.pin() + " " + angle).waitFor();
			Thread.sleep(servoParameters.delay());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void push() {
		rotate(servoParameters.pushAngle());
	}

	@Override
	public void pull() {
		rotate(servoParameters.pullAngle());
	}
}