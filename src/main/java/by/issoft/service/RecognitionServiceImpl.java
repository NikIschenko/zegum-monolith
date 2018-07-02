package by.issoft.service;

import by.issoft.service.dto.JwtToken;
import by.issoft.service.dto.RecognizedResult;
import feign.*;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static by.issoft.ApplicationVariables.*;

public class RecognitionServiceImpl {
	public static RecognizedResult lastRecognitionResult = null;

	public static String authenticate() {
		AuthenticationEndpoints authenticationEndpoints = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.logger(new Slf4jLogger(AuthenticationEndpoints.class))
				.logLevel(Logger.Level.FULL)
				.target(AuthenticationEndpoints.class, SERVER_URI.toASCIIString());
		JwtToken jwtToken = authenticationEndpoints.authenticate(USER_NAME, PASSWORD, false);
		return jwtToken.getToken();
	}

	public static void uploadPhoto(BufferedImage photo) {
		String token = authenticate();
		RecognitionEndpoints recognitionEndpoints = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new FormEncoder())
				.decoder(new JacksonDecoder())
				.logger(new Slf4jLogger(RecognitionEndpoints.class))
				.logLevel(Logger.Level.FULL)
				.requestInterceptor(new AuthenticationInterceptor(token))
				.target(RecognitionEndpoints.class, SERVER_URI.toASCIIString());
		lastRecognitionResult = recognitionEndpoints.uploadPhoto(byteArray(photo));
	}

	private static byte[] byteArray(BufferedImage photo) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(photo, "jpg", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static class AuthenticationInterceptor implements RequestInterceptor {
		private String authenticationToken;

		public AuthenticationInterceptor(String authenticationToken) {
			this.authenticationToken = authenticationToken;
		}

		@Override
		public void apply(RequestTemplate requestTemplate) {
			requestTemplate.header("Authorization", "Bearer " + authenticationToken);
		}
	}
}