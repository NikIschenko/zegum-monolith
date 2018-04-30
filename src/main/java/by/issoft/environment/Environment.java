package by.issoft.environment;

import by.issoft.environment.camera.Camera;
import by.issoft.environment.camera.CameraLogging;
import by.issoft.environment.camera.NativeCamera;
import by.issoft.environment.camera.WebCamera;
import by.issoft.environment.screen.Screen;
import by.issoft.environment.servo.*;

import java.io.IOException;

public class Environment implements Device {
	private final Servo servo;
	private final Camera camera;
	private final Screen screen;

	public Environment(String environmentName, RotationParameters rotationParameters) {
		switch (environmentName.toLowerCase()) {
			case "rasp": {
				int defaultServoPin = 26;
				servo = new ServoLogging(new GumsServo(defaultServoPin, rotationParameters));
				camera = new CameraLogging(new NativeCamera());
				screen = new Screen(480, 320, true);
				break;
			}
			case "desk": {
				servo = new ServoLogging(new FakeServo());
				camera = new CameraLogging(new WebCamera());
				screen = new Screen(480, 320, false);
				break;
			}
			default: {
				throw new IllegalArgumentException("Unavailable Environment name: '" + environmentName + "'.");
			}
		}
	}

	@Override
	public void initialize() {
		try {
			servo.initialize();
			camera.initialize();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public Servo servo() {
		return servo;
	}

	public Camera camera() {
		return camera;
	}

	public Screen screen() {
		return screen;
	}
}
