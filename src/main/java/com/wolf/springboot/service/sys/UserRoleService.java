package com.wolf.springboot.service.sys;

import java.io.Serializable;
import java.util.List;

import com.wolf.springboot.domain.sys.UserRole;
import com.wolf.springboot.service.BaseService;

public interface UserRoleService extends BaseService<UserRole, Serializable>{
	
	public List<UserRole> findAllByUserId(Long userId);

}
