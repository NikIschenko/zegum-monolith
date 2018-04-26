package by.issoft.environment.servo;

public class ServoParameters {
	private final int pin;
	private final int delay;
	private final int pushAngle;
	private final int pullAngle;

	public ServoParameters(int pin, int delay, int pushAngle, int pullAngle) {
		this.pin = pin;
		this.delay = delay;
		this.pushAngle = pushAngle;
		this.pullAngle = pullAngle;
	}

	public ServoParameters(int pushAngle, int pullAngle) {
		this(26, 2000, pushAngle, pullAngle);
	}

	public int pin() {
		return pin;
	}

	public int delay() {
		return delay;
	}

	public int pushAngle() {
		return pushAngle;
	}

	public int pullAngle() {
		return pullAngle;
	}
}