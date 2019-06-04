package com.wolf.springboot.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wolf.springboot.domain.sys.UserRole;
import com.wolf.springboot.domain.sys.UserRoleRepository;
import com.wolf.springboot.service.BaseServiceImpl;

@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, Long, UserRoleRepository>
		implements UserRoleService {

	@Autowired
	private UserRoleRepository dao;

	@Cacheable(cacheNames = { "findAllByUserId" })
	@Override
	public List<UserRole> findAllByUserId(Long userId) {
		Assert.notNull(userId, "userId must be not null");
		return this.dao.findAllByUserId(userId);
	}

}
