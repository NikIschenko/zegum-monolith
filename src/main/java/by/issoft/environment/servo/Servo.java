package by.issoft.environment.servo;

import by.issoft.environment.Device;

public interface Servo extends Device {
	void rotate(final int angle);

	void push();
	void pull();

	void pushAndPull();
}