package com.wolf.springboot.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.wolf.springboot.entity.domain.Criteria;
import com.wolf.springboot.entity.domain.LayPage;

/**
 * 
 * <p>
 * Title: BaseService
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月17日
 */
@SuppressWarnings("rawtypes")
public interface BaseService<T, ID extends Serializable> {

	public T getOne(ID id);

	public T getByUuid(String uuid);

	public List<T> getByProperty(String filed, Object value, boolean ignoreNull);

	public boolean existsById(ID id);

	public List<T> findAll();

	public List<T> findAllById(List<ID> ids);

	public List<T> findAll(Sort sort);

	public List<T> findAll(Criteria<T> criteria);

	public List<T> findAll(Criteria<T> criteria, Sort sort);

	public Page<T> findAll(Pageable pageable);

	public Page<T> findAll(Criteria<T> criteria, Pageable pageable);

	public LayPage<T> findPage(LayPage<T> page);

	public LayPage<T> findPage(Criteria<T> criteria, LayPage<T> page);

	public LayPage<T> findPage(Criteria<T> criteria, Sort sort, LayPage<T> page);

	public void deleteById(ID id);

	public void delete(T entity);

	public void deleteAll(Iterable<? extends T> entities);

	public long count();

	public long count(Criteria<T> criteria);

	public T save(T entity);

	public T saveAndFlush(T entity);

	public List<T> saveAll(Iterable<T> entities);
	
	public List getSQL(String sql, Object... param);
	
	public Object getSQLRetUnique(String sql, Object... param);
	
	public List getSQL(String sql, Integer page, Integer rows, Object... param);
	
	public List getMapBySQL(String sql, Object... param);
	
	public List getMapBySQL(String sql, Integer page, Integer rows, Object... param);
	
	public Integer executeSQL(String sql, Object... param);
	
	public Object executeSQLRetUnique(String sql, Object... param);
	
}
