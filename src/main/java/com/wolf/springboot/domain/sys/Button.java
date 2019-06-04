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

import com.wolf.springboot.domain.AbstractDomain;
import com.wolf.springboot.enums.ButtonCategory;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: Button
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月17日
 */
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "SYS_BUTTON")
public class Button extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 父按钮
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Button parent;

	/**
	 * 所属模块
	 */
	@Column
	private Long moduleId;

	/**
	 * 按钮分类
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private ButtonCategory category;

	/**
	 * 编码
	 */
	@Column(length = 32)
	private String code;

	/**
	 * 名称
	 */
	@Column(length = 64)
	private String name;

	/**
	 * 图标
	 */
	@Column(length = 32)
	private String icon;

	/**
	 * 按钮样式
	 */
	@Column(length = 50)
	private String styleClass;

	/**
	 * js方法
	 */
	@Column(length = 50)
	private String jsEvent;

	/**
	 * Action事件地址
	 */
	@Column(length = 200)
	private String actionEvent;

	/**
	 * 分割线
	 */
	@Column
	private Boolean split = false;

	/**
	 * 有效
	 */
	@Column
	private Boolean alive = false;

	/**
	 * 排序码
	 */
	@Column
	private Integer sortCode = 0;

	/**
	 * 备注
	 */
	@Column(length = 200)
	private String remark;

	/**
	 * 子按钮
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@OrderBy(value = "sortCode asc")
	private List<Button> children = new ArrayList<Button>();

}
