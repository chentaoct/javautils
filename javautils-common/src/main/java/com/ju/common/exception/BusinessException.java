/**
 * @(#)BusinessException.java 2015年12月11日 Copyright 2015 it.kedacom.com, Inc.
 *                            All rights reserved.
 */

package com.ju.common.exception;

/**
 * 业务异常
 * @author chentao
 * @date 2015年12月11日
 */

public class BusinessException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 7313499560838524405L;
	protected String code;// 错误码

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 */

	public BusinessException() {
		super();
	}

	/**
	 * @param message
	 */

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, String code) {
		super(message);
		this.code = code;
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */

	public BusinessException(Throwable cause) {
		super(cause);
	}

}
