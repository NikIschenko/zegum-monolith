package by.issoft.environment.servo;

import by.issoft.environment.Device;

public interface Servo extends Device {
	void rotate(int angle);

	void push();
	void pull();

	default void pushAndPull() {
		push();
		pull();
	}
}