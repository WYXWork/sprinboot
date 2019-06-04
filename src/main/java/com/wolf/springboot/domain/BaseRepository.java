package com.wolf.springboot.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * <p>
 * Title: BaseRepository
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月16日
 */
@SuppressWarnings("rawtypes")
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
	
	public List findSql(String sql, Object... param);
	
	public Object findSQLRetUnique(String sql, Object... param);
	
	public List findSQL(String sql, Integer page, Integer rows, Object... param);
	
	public List findMapBySQL(String sql, Object... param);
	
	public List findMapBySQL(String sql, Integer page, Integer rows, Object... param);
	
	public Integer executeSQL(String sql, Object... param);
	
	public Object executeSQLRetUnique(String sql, Object... param);

}
