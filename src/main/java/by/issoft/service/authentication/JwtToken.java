package by.issoft.service.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtToken {
	@JsonProperty("id_token")
	private String token;

	public String token() {
		return token;
	}
}