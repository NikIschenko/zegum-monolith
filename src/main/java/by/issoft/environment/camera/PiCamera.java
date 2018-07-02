package by.issoft.environment.camera;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PiCamera implements Camera {
	private final Camera camera;

	static {
		Webcam.setDriver(new V4l4jDriver());
	}

	public PiCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public Webcam webcam() {
		return camera.webcam();
	}

	@Override
	public BufferedImage photo() {
		return camera.webcam().getImage();
	}

	@Override
	public void initialize() throws IOException, InterruptedException {
		// custom webcam driver for ARM devices
		Runtime.getRuntime().exec("sudo modprobe bcm2835-v4l2").waitFor();
	}
}