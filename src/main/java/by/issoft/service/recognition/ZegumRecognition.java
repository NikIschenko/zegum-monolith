package by.issoft.service.recognition;

import by.issoft.service.authentication.Authentication;
import by.issoft.service.authentication.AuthenticationInterceptor;
import feign.*;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

public class ZegumRecognition implements Recognition {
	private final RecognitionEndpoints recognitionEndpoints;
	private RecognizedResult recognizedResult;

	public ZegumRecognition(final URI serverUri, final Authentication authentication) {
		if (authentication.token()==null) {
			authentication.authenticate();
		}
		recognitionEndpoints = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new FormEncoder())
				.decoder(new JacksonDecoder())
				.logger(new Slf4jLogger(RecognitionEndpoints.class))
				.logLevel(Logger.Level.FULL)
				.requestInterceptor(new AuthenticationInterceptor(authentication.token()))
				.target(RecognitionEndpoints.class, serverUri.toASCIIString());
	}

	public void uploadPhoto(BufferedImage photo) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(photo, "jpg", baos);
		recognizedResult = recognitionEndpoints.uploadPhoto(baos.toByteArray());
	}
	public RecognizedResult recognitionResult() {
		return recognizedResult;
	}
}