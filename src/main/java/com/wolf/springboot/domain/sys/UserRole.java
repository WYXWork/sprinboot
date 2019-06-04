package com.wolf.springboot.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wolf.springboot.domain.AbstractDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: UserRole
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月15日
 */
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "SYS_USER_ROLE")
public class UserRole extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Long userId;

	@OneToOne
	@JoinColumn(nullable = true)
	private Role role;

}
