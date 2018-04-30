package by.issoft.environment.servo;

import java.io.IOException;

public class GumsServo implements Servo {
	private final int pin;
	private final RotationParameters rotationParameters;

	public GumsServo(int pin, RotationParameters rotationParameters) {
		this.pin = pin;
		this.rotationParameters = rotationParameters;
	}

	@Override
	public void initialize() {
		try {
			Runtime.getRuntime().exec("gpio mode " + pin + " pwm").waitFor();
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
			Runtime.getRuntime().exec("gpio pwm " + pin + " " + angle).waitFor();
			Thread.sleep(rotationParameters.delay());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void push() {
		rotate(rotationParameters.pushAngle());
	}

	@Override
	public void pull() {
		rotate(rotationParameters.pullAngle());
	}
}