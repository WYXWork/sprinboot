package com.wolf.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wolf.springboot.service.CustomUserDetailsService;

/**
 * 
 * <p>
 * Title: WebSecurityConfig
 * </p>
 * <p>
 * Description: spring Security 权限控制
 * </p>
 * 
 * @author wyx
 * @date 2019年4月8日
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsServ;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 启用iframe
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/").permitAll().and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/login").permitAll().and().rememberMe().and().csrf().disable().cors();
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsServ).passwordEncoder(new MyPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/lib/**", "/fonts/**", "/js/**", "/css/**", "/images/**");
	}

	static class MyPasswordEncoder implements PasswordEncoder {

		@Override
		public String encode(CharSequence charSequence) {
			return charSequence.toString();
		}

		@Override
		public boolean matches(CharSequence charSequence, String s) {
			return s.equals(charSequence.toString());
		}
	}

}
