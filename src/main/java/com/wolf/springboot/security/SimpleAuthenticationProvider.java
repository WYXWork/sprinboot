package com.wolf.springboot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.wolf.springboot.domain.sys.User;
import com.wolf.springboot.domain.sys.UserRole;
import com.wolf.springboot.service.CustomUserDetailsService;
import com.wolf.springboot.service.sys.UserRoleService;
import com.wolf.springboot.service.sys.UserService;

/**
 * 
 * <p>
 * Title: SimpleAuthenticationProvider
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年5月16日
 */
@Component
public class SimpleAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();
		// LoginWebAuthenticationDetails details = (LoginWebAuthenticationDetails)
		// authentication.getDetails();
		// String code = details.getCode();
		// String session_code = details.getSession_code();
		// System.err.println("userName:" + userName + " password:" + password);
		// System.err.println("code:" + code + " session_code:" + session_code);
		// if (session_code == null || !session_code.equalsIgnoreCase(code)) {
		// throw new AuthenticationException("验证码错误！") {
		// };
		// }
		User user = userService.getByUsername(userName);
		if (!user.getPassword().equals(password)) {
			throw new AuthenticationException("密码错误！") {
			};
		}
		Collection<GrantedAuthority> authorities = (null == user.getAuthorities() ? new ArrayList<>()
				: user.getAuthorities());
		List<UserRole> roles = userRoleService.findAllByUserId(user.getId());
		for (UserRole role : roles) {
			GrantedAuthority simple = new SimpleGrantedAuthority(role.getRole().getCode());
			authorities.add(simple);
		}
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 这里直接改成retrun true;表示是支持这个执行
		return true;
	}

}
