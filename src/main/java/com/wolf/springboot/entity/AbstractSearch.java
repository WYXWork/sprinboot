package com.wolf.springboot.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractSearch implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String keyValue;

	private String guuid;

	private Long searchId;

	private String keywords;

	private String keyword;

	private String searchCategory;

	private String beginDate;

	private String endDate;

	private String searchField;

	private String searchSid;

	private String searchType;

	private Integer searchYear;

	private String selectedVal;

	private String ztreePids;

	private String ztreePid;

	private String ztreeId;

	private Integer ztreeLevel;

	private String ztreeKey;

	private Boolean backQuery;

	/**
	 * delete ids
	 */
	private String delIds;

	/**
	 * jqgrid/lapage curr page
	 */
	private Integer page;

	/**
	 * jqgrid page data num
	 */
	private Integer rows;

	/**
	 * jqgrid index order
	 */
	private String sidx;


	/**
	 * la pagesize
	 */
	private Integer limit;

	private String childType;

	private Boolean selected;

	/**
	 * -1:all 0:false 1:true
	 */
	private Integer searchAlive;

	/**
	 * -1:all 0:false 1:true
	 */
	private Integer searchPay;

	private Boolean needExpand;

	/**
	 * 默认 ztree ,1:dtree
	 */
	private Integer treeType;

}
