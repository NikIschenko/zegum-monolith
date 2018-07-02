package by.issoft.environment;

import by.issoft.environment.camera.Camera;
import by.issoft.environment.servo.*;

public interface Environment extends Device {
	Servo servo();
	Camera camera();
}
