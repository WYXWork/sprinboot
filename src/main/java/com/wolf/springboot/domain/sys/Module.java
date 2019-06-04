package com.wolf.springboot.domain.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wolf.springboot.domain.AbstractDomain;
import com.wolf.springboot.enums.ModuleCategory;
import com.wolf.springboot.enums.ModuleTarget;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: Module
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
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="SYS_MODULE")
@JsonIgnoreProperties(value = {"parent"})
public class Module extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 64)
	private String name;

	@Column(length = 64)
	private String code;

	@Column(length = 128)
	private String url;

	@Column(length = 64)
	private String icon;

	@Column
	private Integer level = 0;

	@Enumerated(EnumType.STRING)
	@Column(name = "target", length = 10, nullable = false)
	private ModuleTarget target = ModuleTarget.iframe;

	@Enumerated(EnumType.STRING)
	@Column(name = "category", length = 10, nullable = false)
	private ModuleCategory category = ModuleCategory.页面;

	@Column
	private Boolean alive = false;

	@Column
	private Boolean expend = false;

	@Column
	private Integer sortCode = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Module parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy(value = "sortCode asc")
	private List<Module> childrens = new ArrayList<>();

}
