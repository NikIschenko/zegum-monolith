package by.issoft.environment.camera;

import by.issoft.environment.Device;
import com.github.sarxos.webcam.Webcam;

import java.awt.image.BufferedImage;

public interface Camera extends Device {
	Webcam webcam();
	BufferedImage photo();
}