package by.issoft.service.recognition;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Recognition {
	void uploadPhoto(BufferedImage photo) throws IOException;
	RecognizedResult recognitionResult();
}