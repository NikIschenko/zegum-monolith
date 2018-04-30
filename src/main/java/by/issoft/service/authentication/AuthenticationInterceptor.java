package by.issoft.service.authentication;

import feign.RequestInterceptor;
import feign.RequestTemplate;

//sorry guys for name with "-or" ending :)
public class AuthenticationInterceptor implements RequestInterceptor {
	private final String authenticationToken;

	public AuthenticationInterceptor(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.header("Authorization", "Bearer " + authenticationToken);
	}
}