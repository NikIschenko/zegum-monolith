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
		logger.info("Logging by camera class '" + this.getClass().getSimpleName() + "' was started");
	}

	public CameraLogging(final Camera camera) {
		this(camera,  LoggerFactory.getLogger(camera.getClass()));
	}

	@Override
	public Webcam camera() {
		return camera.camera();
	}

	@Override
	public BufferedImage photo() {
		BufferedImage photo = camera.photo();
		logger.info("Photo with resolution: "
				+ camera.camera().getViewSize().width + "x" + camera.camera().getViewSize().height
				+ " was taken");
		return photo;
	}

	@Override
	public void initialize() {
		logger.info("Camera's initialization starts");
		try {
			camera.initialize();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			logger.error("Camera can't be initialized");
		}
		logger.info("Camera's initialization was done");
	}
}