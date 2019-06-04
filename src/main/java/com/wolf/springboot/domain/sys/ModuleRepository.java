package com.wolf.springboot.domain.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wolf.springboot.domain.BaseRepository;

/**
 * 
 * <p>Title: ModuleRepository</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年4月16日
 */
@Repository
public interface ModuleRepository extends BaseRepository<Module, Long> {
	
	public List<Module> findAllByDeletedFalseAndLevel(Integer level);
	
}
