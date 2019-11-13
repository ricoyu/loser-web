package com.loserico.web.exception;

import java.util.List;

import static java.text.MessageFormat.format;

/**
 * 通用异常类,无国际化消息
 * <p>
 * Copyright: Copyright (c) 2018-04-11 11:21
 * <p>
 * Company: DataSense
 * <p>
 * @author Rico Yu	ricoyu520@gmail.com
 * @version 1.0
 * @on
 */
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -6377039158210405281L;

	private int statusCode = 500;

	private Object defaultMessage;

	public CommonException(int statusCode, Object message) {
		this.statusCode = statusCode;
		this.defaultMessage = message;
	}

	public CommonException(int statusCode, String messageTemplate, List<?> params) {
		this.statusCode = statusCode;
		Object[] paramArr = params.stream().toArray(Object[]::new);
		this.defaultMessage = format(messageTemplate, paramArr);
	}

	public CommonException(String[] datas) {
		this.statusCode = Integer.parseInt(datas[0]);
		this.defaultMessage = datas[1];
	}

	public CommonException(int statusCode, String messsage, Throwable cause) {
		super(cause);
		this.statusCode = statusCode;
		this.defaultMessage = messsage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return getLocalizedMessage();
	}

	public Object getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(Object defaultMessage) {
		this.defaultMessage = defaultMessage;
	}
}