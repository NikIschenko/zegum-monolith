package by.issoft.gui.frame;

import by.issoft.environment.EnvironmentType;
import by.issoft.environment.camera.Camera;
import by.issoft.environment.camera.PiCamera;
import by.issoft.environment.camera.WebCamera;
import by.issoft.environment.servo.FakeServo;
import by.issoft.environment.servo.GumsServo;
import by.issoft.environment.servo.Servo;
import by.issoft.gui.frame.item.Countdown;
import by.issoft.gui.frame.item.Greeting;
import by.issoft.gui.frame.item.Processed;
import by.issoft.gui.frame.item.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Navigator {
    public List<Frame> frames;
    private int currentFrameIndex = 0;

    public Navigator(boolean initialization, EnvironmentType environmentType) throws IOException, InterruptedException {
        Camera camera;
        Servo servo;
        if (environmentType.equals(EnvironmentType.RASPBERRY)) {
            servo = new GumsServo();
            camera = new PiCamera();
        } else {
            servo = new FakeServo();
            camera = new WebCamera();
        }

        if (initialization) {
            camera.initialize();
            servo.initialize();
        }

        this.frames = new ArrayList<>();
        frames.add(new Greeting(this, camera));
        frames.add(new Countdown(this, camera));
        frames.add(new Spinner(this, camera));
        frames.add(new Processed(this, servo));
    }

    public void showNext() {
        frames.get(currentFrameIndex).hideFrame();
        currentFrameIndex++;
        if (currentFrameIndex==frames.size())
            currentFrameIndex = 0;
        frames.get(currentFrameIndex).showFrame();
    }
}
