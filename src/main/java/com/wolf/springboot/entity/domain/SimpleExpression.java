package com.wolf.springboot.entity.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>Title: SimpleExpression</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年4月17日
 */
@Getter
@Setter
public class SimpleExpression implements Criterion {

	/**
	 * 属性名
	 */
	private String fieldName;
	/**
	 * 对应值
	 */
	private Object value;
	/**
	 * 计算符
	 */
	private Operator operator;

	protected SimpleExpression(String fieldName, Object value, Operator operator) {
		super();
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Path expression;
		// 此处是表关联数据，注意仅限一层关联，如user.address，
		// 查询user的address集合中，address的name为某个值
		if (fieldName.contains(".")) {
			String[] names = StringUtils.split(fieldName, ".");
			// 获取该属性的类型，Set？List？Map？
			expression = root.get(names[0]);
			Class clazz = expression.getJavaType();
			if (clazz.equals(Set.class)) {
				SetJoin setJoin = root.joinSet(names[0]);
				expression = setJoin.get(names[1]);
			} else if (clazz.equals(List.class)) {
				ListJoin listJoin = root.joinList(names[0]);
				expression = listJoin.get(names[1]);
			} else if (clazz.equals(Map.class)) {
				MapJoin mapJoin = root.joinMap(names[0]);
				expression = mapJoin.get(names[1]);
			} else {
				// 是many to one时
				expression = expression.get(names[1]);
			}
		} else {
			// 单表查询
			expression = root.get(fieldName);
		}

		switch (operator) {
		case EQ:
			return builder.equal(expression, value);
		case NE:
			return builder.notEqual(expression, value);
		case LIKE:
			return builder.like((Expression<String>) expression, "%" + value + "%");
		case LT:
			return builder.lessThan(expression, (Comparable) value);
		case GT:
			return builder.greaterThan(expression, (Comparable) value);
		case LTE:
			return builder.lessThanOrEqualTo(expression, (Comparable) value);
		case GTE:
			return builder.greaterThanOrEqualTo(expression, (Comparable) value);
		case IS_MEMBER:
			return builder.isMember(value, expression);
		case IS_NOT_MEMBER:
			return builder.isNotMember(value, expression);
		default:
			return null;
		}
	}

}
