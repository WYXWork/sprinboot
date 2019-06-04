package com.wolf.springboot.enums;

/**
 * 
 * <p>Title: ButtonCategory</p>
 * <p>Description: </p>
 * @author wyx
 * @date 2019年4月17日
 */
public enum ButtonCategory {

	页面工具栏(0), 表格工具栏(1), 右击栏(2), 页面排版(3);

	private final Integer value;

	private ButtonCategory(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

}
