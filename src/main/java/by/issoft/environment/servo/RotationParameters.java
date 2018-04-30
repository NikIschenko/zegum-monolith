package by.issoft.environment.servo;

public class RotationParameters {
	private final int pushAngle;
	private final int pullAngle;
	private final int delay;

	public RotationParameters(int pushAngle, int pullAngle, int delay) {
		this.delay = delay;
		this.pushAngle = pushAngle;
		this.pullAngle = pullAngle;
	}

	public RotationParameters(int pushAngle, int pullAngle) {
		this(pushAngle, pullAngle, 2000);
	}

	public int pushAngle() {
		return pushAngle;
	}

	public int pullAngle() {
		return pullAngle;
	}

	public int delay() {
		return delay;
	}
}