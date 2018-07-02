package by.issoft.environment.camera;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

public abstract class BaseCamera implements Camera {
    private Webcam webcam;
    private Logger log = LoggerFactory.getLogger(PiCamera.class);

    public BaseCamera() {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        this.webcam = webcam;
        log.info("Logging by webcam class '" + this.getClass().getSimpleName() + "' was started");
    }

    @Override
    public Webcam webcam() {
        return webcam;
    }

    @Override
    public BufferedImage photo() {
        BufferedImage photo = webcam().getImage();
        log.info("Photo with resolution: "
                + webcam.getViewSize().width + "x" + webcam.getViewSize().height
                + " was taken");
        return photo;
    }
}
