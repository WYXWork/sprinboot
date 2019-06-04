package com.wolf.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wolf.springboot.service.CustomUserDetailsService;

/**
 * 
 * <p>
 * Title: MultiHttpSecurityConfig
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年5月16日
 */
@EnableWebSecurity
@Configuration
public class MultiHttpSecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsServ;

	@Autowired
	private LoginAuthenticationDetailsSource loginAuthenticationDetailsSource;

	@Autowired
	private SimpleAuthenticationProvider authenticationProvider;

	@Configuration
	@Order(1)
	public class AdmainConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/sys/login").permitAll().antMatchers("/","/sys/**").authenticated().and()
					.formLogin().loginPage("/sys/login").defaultSuccessUrl("/sys/index", true).failureUrl("/sys/login")
					.authenticationDetailsSource(loginAuthenticationDetailsSource).permitAll().and().logout()
					.logoutUrl("/app/logout.json").logoutSuccessUrl("/").and().authorizeRequests().and().headers()
					.frameOptions().disable().and().csrf().disable();
		}

	}

	@Configuration
	@Order(2) 
	public static class OtherSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/lib/**", "/fonts/**", "/js/**", "/css/**", "/images/**").permitAll()
					.and().csrf().disable();
		}
	}

	static class SimplePasswordEncoder implements PasswordEncoder {

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
