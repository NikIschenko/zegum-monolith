package by.issoft.environment.camera;

import com.github.sarxos.webcam.Webcam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class CameraLogging implements Camera {
	private final Camera camera;
	private final Logger logger;

	public CameraLogging(final Camera camera, final Logger logger) {
		this.camera = camera;
		this.logger = logger;
		logger.info("Logging by webcam class '" + this.getClass().getSimpleName() + "' was started");
	}

	public CameraLogging(final Camera camera) {
		this(camera,  LoggerFactory.getLogger(camera.getClass()));
	}

	@Override
	public Webcam webcam() {
		return camera.webcam();
	}

	@Override
	public BufferedImage photo() {
		BufferedImage photo = camera.photo();
		logger.info("Photo with resolution: "
				+ camera.webcam().getViewSize().width + "x" + camera.webcam().getViewSize().height
				+ " was taken");
		return photo;
	}

	@Override
	public void initialize() throws IOException, InterruptedException {
		logger.info("Camera's initialize starts");
		camera.initialize();
		logger.info("Camera's initialize was done");
	}
}