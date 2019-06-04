package com.wolf.springboot.service.sys;

import java.io.Serializable;
import java.util.List;

import com.wolf.springboot.domain.sys.Module;
import com.wolf.springboot.service.BaseService;

/**
 * 
 * <p>Title: ModuleService</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年4月17日
 */
public interface ModuleService extends BaseService<Module, Serializable>{
	
	public List<Module> findAllDeletedFalse(Integer level);
	
}
