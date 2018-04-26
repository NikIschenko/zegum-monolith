package by.issoft.environment.camera;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.ds.v4l4j.V4l4jDriver;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class NativeCamera implements Camera {
	private Webcam webcam;

	public NativeCamera() {
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		this.webcam = webcam;
	}

	@Override
	public Webcam camera() {
		return webcam;
	}

	@Override
	public BufferedImage photo() {
		return camera().getImage();
	}

	@Override
	public void initialize() throws IOException, InterruptedException {
		Webcam.setDriver(new V4l4jDriver());
		// set camera
		Runtime.getRuntime().exec("sudo modprobe bcm2835-v4l2").waitFor();
	}
}