package com.wolf.springboot.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wolf.springboot.domain.sys.User;
import com.wolf.springboot.domain.sys.UserRepository;
import com.wolf.springboot.service.BaseServiceImpl;

/**
 * 
 * <p>
 * Title: UserServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月17日
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {

	@Autowired
	private UserRepository dao;

	@Override
	public User getByUsername(String username) {
		Assert.notNull(username, "username must be not null");
		return this.dao.findByUsername(username);
	}

}
