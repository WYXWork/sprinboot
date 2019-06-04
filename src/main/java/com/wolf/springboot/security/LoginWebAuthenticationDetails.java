package com.wolf.springboot.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.wolf.springboot.config.SysConst;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>Title: LoginWebAuthenticationDetails</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年5月16日
 */
@Getter
@Setter
public class LoginWebAuthenticationDetails extends WebAuthenticationDetails {

	private String code;

	private String session_code;

	public LoginWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.code = (String) request.getParameter("code");
		this.session_code = (String) request.getSession().getAttribute(SysConst.ADMAIN_SESSION_VERIGY_CODE);
	}

}
