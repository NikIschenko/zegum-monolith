package by.issoft.environment;

import by.issoft.environment.camera.Camera;
import by.issoft.environment.camera.CameraLogging;
import by.issoft.environment.camera.PiCamera;
import by.issoft.environment.camera.WebCamera;
import by.issoft.environment.servo.*;

import java.io.IOException;

public class RaspberryEnvironment implements Environment {
	private final Servo servo;
	private final Camera camera;

	public RaspberryEnvironment(final RotationParameters rotationParameters) {
		int defaultServoPin = 26;
		servo = new ServoLogging(new GumsServo(defaultServoPin, rotationParameters));
		camera = new CameraLogging(new PiCamera(new WebCamera()));
	}

	@Override
	public void initialize() throws IOException, InterruptedException {
		servo.initialize();
		camera.initialize();
	}

	public Servo servo() {
		return servo;
	}

	public Camera camera() {
		return camera;
	}
}
