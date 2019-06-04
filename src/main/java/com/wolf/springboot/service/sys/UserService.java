package com.wolf.springboot.service.sys;

import java.io.Serializable;

import com.wolf.springboot.domain.sys.User;
import com.wolf.springboot.service.BaseService;

public interface UserService extends BaseService<User, Serializable>{

	public User getByUsername(String username);
	
}
