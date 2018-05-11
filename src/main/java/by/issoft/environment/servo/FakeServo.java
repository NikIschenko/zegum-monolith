package by.issoft.environment.servo;

public class FakeServo implements Servo {
	@Override
	public void initialize() { }

	@Override
	public void rotate(final int angle) {	}

	@Override
	public void push() { }
	@Override
	public void pull() { }

	@Override
	public void pushAndPull() {	}
}