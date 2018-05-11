package by.issoft.service.recognition;

import by.issoft.service.authentication.Authentication;
import by.issoft.service.authentication.AuthenticationInterceptor;
import feign.*;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

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

	@Override
	public void uploadPhoto(final byte[] file) {
		// TODO: add 403 error processing
		recognizedResult = recognitionEndpoints.uploadPhoto(file);
	}

	@Override
	public RecognizedResult recognitionResult() {
		return recognizedResult;
	}
}