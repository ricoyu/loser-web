package com.loserico.common.web.exception;

/**
 * 业务代码无法处理或者处理不到的错误 
 * <p>
 * Copyright: Copyright (c) 2019-10-14 16:27
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>
 
 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

}