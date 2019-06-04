package com.wolf.springboot.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.wolf.springboot.domain.sys.Module;
import com.wolf.springboot.domain.sys.ModuleRepository;
import com.wolf.springboot.service.BaseServiceImpl;

/**
 * 
 * <p>
 * Title: ModuleServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月16日
 */
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module, Long, ModuleRepository> implements ModuleService {
	
	@Autowired
	private ModuleRepository dao;

//	@Cacheable("find_all_by_deleted_false_and_level")
	@CachePut("find_all_by_deleted_false_and_level")
	@Override
	public List<Module> findAllDeletedFalse(Integer level) {
		return dao.findAllByDeletedFalseAndLevel(level);
	}

}
