package com.wolf.springboot.enums;

/**
 * 
 * <p>
 * Title: DispatcherSuffixCategory
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月15日
 */
public enum DispatcherSuffixCategory {
	
	themeyleaf(".html"), freemarker(".ftl");

	private final String suffix;

	private DispatcherSuffixCategory(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

}
