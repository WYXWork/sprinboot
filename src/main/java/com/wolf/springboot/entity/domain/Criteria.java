package com.wolf.springboot.entity.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: Criteria
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
public class Criteria<T> implements Specification<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Criterion> criterions = new ArrayList<>();

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (!criterions.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
			for (Criterion c : criterions) {
				predicates.add(c.toPredicate(root, query, builder));
			}
			if (predicates.size() > 0) {
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}
		return builder.conjunction();
	}

	public void add(Criterion criterion) {
		if (criterion != null) {
			criterions.add(criterion);
		}
	}

}
