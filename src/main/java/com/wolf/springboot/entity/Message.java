package com.wolf.springboot.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: Message
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年5月16日
 */
@Getter
@Setter
public class Message implements Serializable {

	enum StatusCodeEnum {

		SUCCESS(1), WARNING(2), ERROR(-1), DEFALUT(0);

		private StatusCodeEnum(Integer value) {
			this.value = value;
		}

		private final Integer value;
	}

	private String message;

	private StatusCodeEnum statusCode = StatusCodeEnum.DEFALUT;

	private String forwordUrl;

	private Object obj;

	public void successMsg(String msg) {
		this.message = msg;
		this.statusCode = StatusCodeEnum.SUCCESS;
	}

	public void successMsgAndObj(String msg, Object obj) {
		this.successMsg(msg);
		this.obj = obj;
	}

	public void errorMsg(String msg) {
		this.message = msg;
		this.statusCode = StatusCodeEnum.ERROR;
	}

	public void errorMsgAndObj(String msg, Object obj) {
		this.errorMsg(msg);
		this.obj = obj;
	}

}
