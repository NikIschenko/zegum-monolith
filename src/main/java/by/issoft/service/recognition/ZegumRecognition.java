package by.issoft.service.recognition;

import by.issoft.service.authenticate.Authentication;
import feign.*;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class ZegumRecognition implements Recognition {
	private final RecognitionEndpoints recognitionEndpoints;
	private final Authentication authentication;
	private RecognizedResult recognizedResult;

	public ZegumRecognition(String serverUrl, Authentication authentication) {
		this.authentication = authentication;
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
				.target(RecognitionEndpoints.class, serverUrl);
	}

	@Override
	public void uploadPhoto(byte[] file) {
		// TODO: add 403 error processing
		recognizedResult = recognitionEndpoints.uploadPhoto(file);
	}

	@Override
	public RecognizedResult recognitionResult() {
		return recognizedResult;
	}
}