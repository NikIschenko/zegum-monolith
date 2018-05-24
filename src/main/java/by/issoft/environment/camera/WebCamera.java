package by.issoft.environment.camera;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.image.BufferedImage;

public class WebCamera implements Camera {
	private final Webcam webcam;

	public WebCamera() {
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		this.webcam = webcam;
	}

	@Override
	public Webcam webcam() {
		return webcam;
	}

	@Override
	public BufferedImage photo() {
		return webcam().getImage();
	}

	@Override
	public void initialize() {

	}
}