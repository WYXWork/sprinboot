package com.wolf.springboot.entity.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 
 * <p>
 * Title: Criterion
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月16日
 */
public interface Criterion {

	enum Operator {
		EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR, IS_MEMBER, IS_NOT_MEMBER
	}

	Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);

}
