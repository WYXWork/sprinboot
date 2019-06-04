package com.wolf.springboot.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: BaseRepositoryImpl
 * </p>
 * <p>
 * Description:拓展sql 语句
 * </p>
 * 
 * @author wyx
 * @date 2019年4月16日
 */
@Getter
@Setter
@SuppressWarnings("rawtypes")
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	private Logger logger = LogManager.getLogger(BaseRepositoryImpl.class);

	private final EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public List findSql(String sql, Object... param) {
		Query q = entityManager.createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.getResultList();
	}

	@Override
	public Object findSQLRetUnique(String sql, Object... param) {
		Query q = entityManager.createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.getSingleResult();
	}

	@Override
	public List findSQL(String sql, Integer page, Integer rows, Object... param) {
		page = page == null || page < 1 ? 1 : page;
		rows = rows == null || rows < 1 ? 10 : rows;
		Query q = entityManager.createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
	}

	@Override
	public List findMapBySQL(String sql, Object... param) {
		Query q = entityManager.createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List findMapBySQL(String sql, Integer page, Integer rows, Object... param) {
		page = page == null || page < 1 ? 1 : page;
		rows = rows == null || rows < 1 ? 10 : rows;
		Query q = entityManager.createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public Integer executeSQL(String sql, Object... param) {
		Query q = entityManager.createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Object executeSQLRetUnique(String sql, Object... param) {
		Query q = entityManager.createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.getSingleResult();
	}

}
