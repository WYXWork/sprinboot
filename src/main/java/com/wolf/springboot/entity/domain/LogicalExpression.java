package com.wolf.springboot.entity.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: LogicalExpression
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月16日
 */
@Getter
@Setter
public class LogicalExpression implements Criterion {

	/**
	 * 逻辑表达式中包含的表达式
	 */
	private Criterion[] criterion;
	/**
	 * 计算符
	 */
	private Operator operator;

	public LogicalExpression(Criterion[] criterions, Operator operator) {
		this.criterion = criterions;
		this.operator = operator;
	}

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		for (int i = 0; i < this.criterion.length; i++) {
			predicates.add(this.criterion[i].toPredicate(root, query, builder));
		}
		switch (operator) {
		case OR:
			return builder.or(predicates.toArray(new Predicate[predicates.size()]));
		default:
			return null;
		}
	}

}
