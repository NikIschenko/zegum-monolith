package by.issoft.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtToken {
	@JsonProperty("id_token")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}