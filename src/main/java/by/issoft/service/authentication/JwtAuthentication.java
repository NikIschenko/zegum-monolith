package by.issoft.service.authentication;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.net.URI;

public class JwtAuthentication implements Authentication {
	private final AuthenticationEndpoints authenticationEndpoints;
	private final String username;
	private final String password;
	private JwtToken jwtToken;

	public JwtAuthentication(URI serverUri, String username, String password) {
		this.username = username;
		this.password = password;

		authenticationEndpoints = Feign.builder()
				.client(new OkHttpClient())
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.logger(new Slf4jLogger(AuthenticationEndpoints.class))
				.logLevel(Logger.Level.FULL)
				.target(AuthenticationEndpoints.class, serverUri.toASCIIString());
	}

	@Override
	public void authenticate() {
		jwtToken = authenticationEndpoints.authenticate(username, password, true);
	}

	@Override
	public String token() {
		return jwtToken == null ? null : jwtToken.token();
	}
}

