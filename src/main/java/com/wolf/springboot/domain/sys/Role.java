package com.wolf.springboot.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.wolf.springboot.domain.AbstractDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="SYS_ROLE")
public class Role extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String code;

}
