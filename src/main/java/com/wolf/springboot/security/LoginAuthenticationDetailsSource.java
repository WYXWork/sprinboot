package com.wolf.springboot.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>Title: LoginAuthenticationDetailsSource</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年5月16日
 */
@Component
public class LoginAuthenticationDetailsSource
		implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new LoginWebAuthenticationDetails(context);
	}

}
