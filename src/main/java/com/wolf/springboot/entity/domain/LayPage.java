package com.wolf.springboot.entity.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: LayPage
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月26日
 */
@Getter
@Setter
public class LayPage<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code = 0;

	private Long count = 0L;

	private String msg = "";

	private Integer page = 0;

	private Integer pageSize = 0;

	private List<T> data = new ArrayList<T>();

	private Object otherData;

}
