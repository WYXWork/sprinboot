package com.wolf.springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wolf.springboot.domain.sys.User;
import com.wolf.springboot.domain.sys.UserRole;
import com.wolf.springboot.service.sys.UserRoleService;
import com.wolf.springboot.service.sys.UserService;

/**
 * 
 * <p>
 * Title: CustomUserDetailsService
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月17日
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("获取用户信息-->用户名为:" + username);
		User user = userService.getByUsername(username);
		if (null == user) {
			logger.info("获取用户信息-->" + username + "失败");
			throw new UsernameNotFoundException("用户名-->：" + username + "不存在!");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		List<UserRole> roles = userRoleService.findAllByUserId(user.getId());
		for (UserRole role : roles) {
			GrantedAuthority simple = new SimpleGrantedAuthority(role.getRole().getCode());
			authorities.add(simple);
		}
		user.setAuthorities(authorities);
		logger.info("获取用户-->:" + username + "信息成功！");
		return user;
	}

}
