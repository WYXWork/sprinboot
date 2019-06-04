package com.wolf.springboot.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wolf.springboot.domain.BaseRepository;
import com.wolf.springboot.entity.domain.Criteria;
import com.wolf.springboot.entity.domain.LayPage;
import com.wolf.springboot.entity.domain.Restrictions;

/**
 * 
 * <p>
 * Title: BaseServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月17日
 */
@SuppressWarnings("all")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BaseServiceImpl<T, ID extends Serializable, R extends BaseRepository<T, ID>>
		implements BaseService<T, Serializable> {

	@Autowired
	private R dao;

	@Override
	public List<T> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<T> findAll(Criteria<T> criteria, Sort sort) {
		return this.dao.findAll(criteria, sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return this.dao.findAll(pageable);
	}

	@Override
	public List<T> findAll(Sort sort) {
		return this.dao.findAll(sort);
	}

	@Override
	public List<T> findAll(Criteria<T> criteria) {
		return this.dao.findAll(criteria);
	}

	@Override
	public Page<T> findAll(Criteria<T> criteria, Pageable pageable) {
		return this.dao.findAll(criteria, pageable);
	}

	@Override
	public void delete(T entity) {
		this.dao.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		this.dao.deleteAll(entities);
	}

	@Override
	public long count() {
		return this.dao.count();
	}

	@Override
	public long count(Criteria<T> criteria) {
		return this.dao.count(criteria);
	}

	@Override
	public T save(T entity) {
		return this.dao.save(entity);
	}

	@Override
	public T saveAndFlush(T entity) {
		return this.dao.saveAndFlush(entity);
	}

	@Override
	public List<T> saveAll(Iterable<T> entities) {
		return this.dao.saveAll(entities);
	}

	@Override
	public T getOne(Serializable id) {
		return this.dao.getOne((ID) id);
	}

	@Override
	public boolean existsById(Serializable id) {
		return this.dao.existsById((ID) id);
	}

	@Override
	public List<T> findAllById(List<Serializable> ids) {
		return this.dao.findAllById((Iterable<ID>) ids);
	}

	@Override
	public void deleteById(Serializable id) {
		this.dao.deleteById((ID) id);
	}

	@Override
	public LayPage<T> findPage(LayPage<T> page) {
		Page<T> result = this.findAll(PageRequest.of(page.getPage(), page.getPageSize()));
		page.setCount(result.getTotalElements());
		page.setData(result.getContent());
		return page;
	}

	@Override
	public LayPage<T> findPage(Criteria<T> criteria, LayPage<T> page) {
		Page<T> result = this.findAll(criteria, PageRequest.of(page.getPage(), page.getPageSize()));
		page.setCount(result.getTotalElements());
		page.setData(result.getContent());
		return page;
	}

	@Override
	public LayPage<T> findPage(Criteria<T> criteria, Sort sort, LayPage<T> page) {
		Page<T> result = this.findAll(criteria, PageRequest.of(page.getPage(), page.getPageSize(), sort));
		page.setCount(result.getTotalElements());
		page.setData(result.getContent());
		return page;
	}

	@Override
	public T getByUuid(String uuid) {
		List<T> results = this.getByProperty("uuid", uuid, true);
		return null != results && !results.isEmpty() ? results.get(0) : null;
	}

	@Override
	public List<T> getByProperty(String filed, Object value, boolean ignoreNull) {
		Criteria<T> criteria = new Criteria<>();
		criteria.add(Restrictions.eq(filed, value, ignoreNull));
		return this.findAll(criteria);
	}

	@Override
	public List getSQL(String sql, Object... param) {
		return this.dao.findSql(sql, param);
	}

	@Override
	public Object getSQLRetUnique(String sql, Object... param) {
		return this.dao.findSQLRetUnique(sql, param);
	}

	@Override
	public List getSQL(String sql, Integer page, Integer rows, Object... param) {
		return this.dao.findSQL(sql, page, rows, param);
	}

	@Override
	public List getMapBySQL(String sql, Object... param) {
		return this.dao.findMapBySQL(sql, param);
	}

	@Override
	public List getMapBySQL(String sql, Integer page, Integer rows, Object... param) {
		return this.dao.findMapBySQL(sql, page, rows, param);
	}

	@Override
	public Integer executeSQL(String sql, Object... param) {
		return this.dao.executeSQL(sql, param);
	}

	@Override
	public Object executeSQLRetUnique(String sql, Object... param) {
		return this.dao.executeSQLRetUnique(sql, param);
	}

}
