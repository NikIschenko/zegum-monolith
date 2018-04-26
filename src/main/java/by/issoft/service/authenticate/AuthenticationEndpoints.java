package by.issoft.service.authenticate;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Accept: application/json")
public interface AuthenticationEndpoints {
	@RequestLine("POST /api/authenticate")
	@Headers("Content-Type: application/json")
	JwtToken authenticate(@Param("username") String username, @Param("password") String password, @Param("rememberMe") boolean rememberMe);
}