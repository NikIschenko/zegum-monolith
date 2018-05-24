package by.issoft.environment;

import by.issoft.environment.camera.Camera;
import by.issoft.environment.camera.CameraLogging;
import by.issoft.environment.camera.NativeCamera;
import by.issoft.environment.camera.WebCamera;
import by.issoft.environment.servo.*;

import java.io.IOException;

public class Environment implements Device {
	private final EnvironmentType environmentType;
	private final Servo servo;
	private final Camera camera;


	public Environment(final EnvironmentType environmentType, final RotationParameters rotationParameters) {
		this.environmentType = environmentType;
		switch (environmentType) {
			case RASPBERRY: {
				int defaultServoPin = 26;
				servo = new ServoLogging(new GumsServo(defaultServoPin, rotationParameters));
				camera = new CameraLogging(new NativeCamera());
				break;
			}
			case DESKTOP: {
				servo = new ServoLogging(new FakeServo());
				camera = new CameraLogging(new WebCamera());
				break;
			}
			default: {
				throw new IllegalArgumentException("Unavailable Environment name: '" + environmentType.toString() + "'.");
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

	public EnvironmentType environmentType() {
		return environmentType;
	}

	public Servo servo() {
		return servo;
	}

	public Camera camera() {
		return camera;
	}
}
