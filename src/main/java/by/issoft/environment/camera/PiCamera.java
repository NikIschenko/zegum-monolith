package by.issoft.environment.camera;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PiCamera extends BaseCamera {
    private Logger log = LoggerFactory.getLogger(PiCamera.class);

    static {
        Webcam.setDriver(new V4l4jDriver());
    }

    @Override
    public void initialize() throws IOException, InterruptedException {
        log.info("Camera's initialize starts");
        Runtime.getRuntime().exec("sudo modprobe bcm2835-v4l2").waitFor();
        log.info("Camera's initialize was done");
    }
}