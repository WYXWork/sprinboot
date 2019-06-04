package com.wolf.springboot.service.sys;

import org.springframework.stereotype.Service;

import com.wolf.springboot.domain.sys.Role;
import com.wolf.springboot.domain.sys.RoleRepository;
import com.wolf.springboot.service.BaseServiceImpl;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleRepository> implements RoleService {


}
