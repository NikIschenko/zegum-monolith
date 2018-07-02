package by.issoft.environment;

import by.issoft.environment.camera.Camera;
import by.issoft.environment.camera.CameraLogging;
import by.issoft.environment.camera.WebCamera;
import by.issoft.environment.servo.*;

import java.io.IOException;

public class DesktopEnvironment implements Environment {
	private final Servo servo;
	private final Camera camera;


	public DesktopEnvironment() {
		servo = new ServoLogging(new FakeServo());
		camera = new CameraLogging(new WebCamera());
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
